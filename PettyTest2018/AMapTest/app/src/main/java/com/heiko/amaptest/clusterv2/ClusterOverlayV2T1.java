package com.heiko.amaptest.clusterv2;

import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.util.LruCache;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.TextView;

import com.amap.api.maps.AMap;
import com.amap.api.maps.AMapUtils;
import com.amap.api.maps.model.BitmapDescriptor;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.LatLngBounds;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.animation.AlphaAnimation;
import com.amap.api.maps.model.animation.Animation;
import com.heiko.amaptest.R;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by yiyi.qi on 16/10/10.
 * 整体设计采用了两个线程,一个线程用于计算组织聚合数据,一个线程负责处理Marker相关操作
 */
public class ClusterOverlayV2T1 implements AMap.OnCameraChangeListener,
        AMap.OnMarkerClickListener {
    private AMap mAMap;
    private Context mContext;
    private List<ClusterItemV2> mClusterItems;
    private List<ClusterV2> mClusters; //点聚合集合
    private int mClusterSize;
    private ClusterClickListenerV2 mClusterClickListener;
    private ClusterRenderV2 mClusterRender;
    private List<Marker> mAddMarkers = new ArrayList<Marker>(); //地图真正的点
    private double mClusterDistance;
    private LruCache<Integer, BitmapDescriptor> mLruCache;
    private HandlerThread mMarkerHandlerThread = new HandlerThread("addMarker");
    private HandlerThread mSignClusterThread = new HandlerThread("calculateCluster");
    private Handler mMarkerhandler;
    private Handler mSignClusterHandler;
    private float mPXInMeters;
    private boolean mIsCanceled = false;

    /**
     * 构造函数,批量添加聚合元素时,调用此构造函数
     *
     * @param context
     * @param amap
     * @param clusterRadius 点聚合半径 (dp)
     * @param imgCacheSize 最多会缓存imgCacheSize张图片作为聚合显示元素图片,根据自己显示需求和app使用内存情况,传相关值
     */
    public ClusterOverlayV2T1(Context context, AMap amap, int clusterRadius, int imgCacheSize) {
        mLruCache = new LruCache<Integer, BitmapDescriptor>(imgCacheSize) {
            protected void entryRemoved(boolean evicted, Integer key,
                                        BitmapDescriptor oldValue,
                                        BitmapDescriptor newValue) {
                oldValue.getBitmap().recycle();
            }
        };
        mClusterItems = new ArrayList<>();
        mContext = context;
        mClusters = new ArrayList<>();
        this.mAMap = amap;
        mClusterSize = ClusterUtils.dp2px(context,clusterRadius);
        mPXInMeters = mAMap.getScalePerPixel();
        mClusterDistance = mPXInMeters * mClusterSize;
        amap.setOnCameraChangeListener(this);
        amap.setOnMarkerClickListener(this);
        initThreadHandler();
        assignClusters();
    }

    /**
     * 设置聚合点的点击事件
     *
     * @param clusterClickListener
     */
    public void setOnClusterClickListener(
            ClusterClickListenerV2 clusterClickListener) {
        mClusterClickListener = clusterClickListener;
    }

    /**
     * 添加一个聚合点
     *
     * @param item
     */
    public void addClusterItem(ClusterItemV2 item) {
        Message message = Message.obtain();
        message.what = SignClusterHandler.CALCULATE_SINGLE_CLUSTER;
        message.obj = item;
        mSignClusterHandler.sendMessage(message);
    }

    /**
     * 设置聚合元素的渲染样式，不设置则默认为气泡加数字形式进行渲染
     *
     * @param render
     */
    public void setClusterRenderer(ClusterRenderV2 render) {
        mClusterRender = render;
    }

    public void onDestroy() {
        mIsCanceled = true;
        mSignClusterHandler.removeCallbacksAndMessages(null);
        mMarkerhandler.removeCallbacksAndMessages(null);
        mSignClusterThread.quit();
        mMarkerHandlerThread.quit();
        for (Marker marker : mAddMarkers) {
            marker.remove();

        }
        mAddMarkers.clear();
        mLruCache.evictAll();
    }

    //初始化Handler
    private void initThreadHandler() {
        mMarkerHandlerThread.start();
        mSignClusterThread.start();
        mMarkerhandler = new MarkerHandler(mMarkerHandlerThread.getLooper());
        mSignClusterHandler = new SignClusterHandler(mSignClusterThread.getLooper());
    }

    @Override
    public void onCameraChange(CameraPosition arg0) {

    }

    @Override
    public void onCameraChangeFinish(CameraPosition arg0) {
        mPXInMeters = mAMap.getScalePerPixel();
        mClusterDistance = mPXInMeters * mClusterSize;
        assignClusters();
    }

    //点击事件
    @Override
    public boolean onMarkerClick(Marker arg0) {
        if (mClusterClickListener == null) {
            return true;
        }
        ClusterV2 cluster = (ClusterV2) arg0.getObject();
        if (cluster != null) {
            mClusterClickListener.onClick(arg0, cluster.getClusterItems());
            return true;
        }
        return false;
    }


    public void clearClusterItems() {
        mClusterItems.clear();
    }

    /**
     * 添加多个聚合点
     *
     * @param items
     */
    public void addClusterItems(List<ClusterItemV2> items) {
        if (items == null) {
            items = new ArrayList<>();
        }
        mClusterItems.addAll(items);
        Message message = Message.obtain();
        message.what = SignClusterHandler.CALCULATE_CLUSTER;
        message.obj = items;
        mSignClusterHandler.sendMessage(message);
    }

    private AlphaAnimation mADDAnimation = new AlphaAnimation(0, 1);

    /**
     * 对点进行聚合
     */
    private void assignClusters() {
        mIsCanceled = true;
        mSignClusterHandler.removeMessages(SignClusterHandler.CALCULATE_CLUSTER);
        mSignClusterHandler.sendEmptyMessage(SignClusterHandler.CALCULATE_CLUSTER);
    }
//-----------------------辅助内部类用---------------------------------------------

    /**
     * marker渐变动画，动画结束后将Marker删除
     */
    class MyAnimationListener implements Animation.AnimationListener {
        private List<Marker> mRemoveMarkers;

        MyAnimationListener(List<Marker> removeMarkers) {
            mRemoveMarkers = removeMarkers;
        }

        @Override
        public void onAnimationStart() {

        }

        @Override
        public void onAnimationEnd() {
            for (Marker marker : mRemoveMarkers) {
                marker.remove();
            }
            mRemoveMarkers.clear();
        }
    }

//-----------------------处理Marker添加、更新操作 START---------------------------------------------

    /**
     * 处理market添加，更新等操作
     */
    class MarkerHandler extends Handler {

        static final int ADD_CLUSTER_LIST = 0;

        static final int ADD_SINGLE_CLUSTER = 1;

        static final int UPDATE_SINGLE_CLUSTER = 2;

        MarkerHandler(Looper looper) {
            super(looper);
        }

        public void handleMessage(Message message) {

            switch (message.what) {
                case ADD_CLUSTER_LIST:
                    List<ClusterV2> clusters = (List<ClusterV2>) message.obj;
                    addClusterToMap(clusters);
                    break;
                case ADD_SINGLE_CLUSTER:
                    ClusterV2 cluster = (ClusterV2) message.obj;
                    addSingleClusterToMap(cluster);
                    break;
                case UPDATE_SINGLE_CLUSTER:
                    ClusterV2 updateCluster = (ClusterV2) message.obj;
                    updateCluster(updateCluster);
                    break;
            }
        }
    }

    /**
     * 将聚合元素添加至地图上
     */
    private void addClusterToMap(List<ClusterV2> clusters) {

        ArrayList<Marker> removeMarkers = new ArrayList<>();
        removeMarkers.addAll(mAddMarkers);
        AlphaAnimation alphaAnimation = new AlphaAnimation(1, 0);
        MyAnimationListener myAnimationListener = new MyAnimationListener(removeMarkers);
        for (Marker marker : removeMarkers) {
            marker.setAnimation(alphaAnimation);
            marker.setAnimationListener(myAnimationListener);
            marker.startAnimation();
        }

        for (ClusterV2 cluster : clusters) {
            addSingleClusterToMap(cluster);
        }
    }

    /**
     * 获取每个聚合点的绘制样式
     */
    private BitmapDescriptor getBitmapDes(int num) {
        BitmapDescriptor bitmapDescriptor = mLruCache.get(num);
        if (bitmapDescriptor == null) {
            TextView textView = new TextView(mContext);
            if (num > 1) {
                String tile = String.valueOf(num);
                textView.setText(tile);
            }
            textView.setGravity(Gravity.CENTER);
            textView.setTextColor(Color.BLACK);
            textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
            if (mClusterRender != null && mClusterRender.getDrawAble(num) != null) {
                textView.setBackgroundDrawable(mClusterRender.getDrawAble(num));
            } else {
                textView.setBackgroundResource(R.drawable.defaultcluster);
            }
            bitmapDescriptor = BitmapDescriptorFactory.fromView(textView);
            mLruCache.put(num, bitmapDescriptor);

        }
        return bitmapDescriptor;
    }

    /**
     * 将单个聚合元素添加至地图显示
     *
     * @param cluster
     */
    private void addSingleClusterToMap(ClusterV2 cluster) {
        LatLng latlng = cluster.getCenterLatLng();
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.anchor(0.5f, 0.5f)
                .icon(getBitmapDes(cluster.getClusterCount())).position(latlng);
        Marker marker = mAMap.addMarker(markerOptions);
        marker.setAnimation(mADDAnimation);
        marker.setObject(cluster);

        marker.startAnimation();
        cluster.setMarker(marker);
        mAddMarkers.add(marker);
    }

    /**
     * 更新已加入地图聚合点的样式
     */
    private void updateCluster(ClusterV2 cluster) {
        Marker marker = cluster.getMarker();
        marker.setIcon(getBitmapDes(cluster.getClusterCount()));
    }

//-----------------------处理Marker添加、更新操作 END---------------------------------------------



//-----------------------处理聚合点算法线程 START---------------------------------------------

    /**
     * 处理聚合点算法线程
     */
    class SignClusterHandler extends Handler {
        static final int CALCULATE_CLUSTER = 0;
        static final int CALCULATE_SINGLE_CLUSTER = 1;

        SignClusterHandler(Looper looper) {
            super(looper);
        }

        public void handleMessage(Message message) {
            switch (message.what) {
                case CALCULATE_CLUSTER:
                    calculateClusters();
                    break;
                case CALCULATE_SINGLE_CLUSTER:
                    ClusterItemV2 item = (ClusterItemV2) message.obj;
                    mClusterItems.add(item);
                    calculateSingleCluster(item);
                    break;
            }
        }
    }

    private void calculateClusters() {
        mIsCanceled = false;
        mClusters.clear();
        LatLngBounds visibleBounds = mAMap.getProjection().getVisibleRegion().latLngBounds;
        for (ClusterItemV2 clusterItem : mClusterItems) {
            if (mIsCanceled) {
                return;
            }
            LatLng latlng = clusterItem.getPosition();
            if (visibleBounds.contains(latlng)) {
                ClusterV2 cluster = getCluster(latlng, mClusters);
                if (cluster != null) {
                    cluster.addClusterItem(clusterItem);
                } else {
                    cluster = new ClusterV2(latlng);
                    mClusters.add(cluster);
                    cluster.addClusterItem(clusterItem);
                }

            }
        }

        //复制一份数据，规避同步
        List<ClusterV2> clusters = new ArrayList<ClusterV2>();
        clusters.addAll(mClusters);
        Message message = Message.obtain();
        message.what = MarkerHandler.ADD_CLUSTER_LIST;
        message.obj = clusters;
        if (mIsCanceled) {
            return;
        }
        mMarkerhandler.sendMessage(message);
    }

    /**
     * 在已有的聚合基础上，对添加的单个元素进行聚合
     *
     * @param clusterItem
     */
    private void calculateSingleCluster(ClusterItemV2 clusterItem) {
        LatLngBounds visibleBounds = mAMap.getProjection().getVisibleRegion().latLngBounds;
        LatLng latlng = clusterItem.getPosition();
        if (!visibleBounds.contains(latlng)) {
            return;
        }
        ClusterV2 cluster = getCluster(latlng, mClusters);
        if (cluster != null) {
            cluster.addClusterItem(clusterItem);
            Message message = Message.obtain();
            message.what = MarkerHandler.UPDATE_SINGLE_CLUSTER;

            message.obj = cluster;
            mMarkerhandler.removeMessages(MarkerHandler.UPDATE_SINGLE_CLUSTER);
            mMarkerhandler.sendMessageDelayed(message, 5);


        } else {

            cluster = new ClusterV2(latlng);
            mClusters.add(cluster);
            cluster.addClusterItem(clusterItem);
            Message message = Message.obtain();
            message.what = MarkerHandler.ADD_SINGLE_CLUSTER;
            message.obj = cluster;
            mMarkerhandler.sendMessage(message);

        }
    }

    /**
     * 根据一个点获取是否可以依附的聚合点，没有则返回null
     *
     * @param latLng
     * @return
     */
    private ClusterV2 getCluster(LatLng latLng, List<ClusterV2> clusters) {
        for (ClusterV2 cluster : clusters) {
            LatLng clusterCenterPoint = cluster.getCenterLatLng();
            double distance = AMapUtils.calculateLineDistance(latLng, clusterCenterPoint);
            if (distance < mClusterDistance && mAMap.getCameraPosition().zoom < 19) {
                return cluster;
            }
        }

        return null;
    }

    //-----------------------处理聚合点算法线程 END---------------------------------------------
}