package com.heiko.amaptest.clusterv3;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.util.LruCache;
import android.widget.ImageView;

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

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class ClusterOverlay implements AMap.OnCameraChangeListener {
    private final Context context;
    private HandlerThread mMarkerHandlerThread = new HandlerThread("addMarker");
    private HandlerThread mSignClusterThread = new HandlerThread("calculateCluster");
    private Handler mMarkerHandler;
    private Handler mSignClusterHandler;
    private LruCache<String, BitmapDescriptor> mLruCache;
    private final AMap aMap;
    private ClusterMeta clusterMeta;

    public ClusterMeta getClusterMeta() {
        return clusterMeta;
    }

    /**
     * Default constructor
     */
    public ClusterOverlay(Context context, AMap aMap, ClusterMeta clusterMeta, int clusterRadius, int imgCacheSize) {
        mLruCache = new LruCache<String, BitmapDescriptor>(imgCacheSize) {
            protected void entryRemoved(boolean evicted, Integer key,
                                        BitmapDescriptor oldValue,
                                        BitmapDescriptor newValue) {
                oldValue.getBitmap().recycle();
            }
        };
        this.context = context;
        this.aMap = aMap;
        ClusterConsts.clusterSize = ClusterUtils.dp2px(context, clusterRadius);
        initThreadHandler();
        if (clusterMeta != null) {
            setClusterMeta(clusterMeta);
        }
    }

    public void onDestroy() {
        ClusterConsts.isCanceled = true;
        mSignClusterHandler.removeCallbacksAndMessages(null);
        mMarkerHandler.removeCallbacksAndMessages(null);
        mSignClusterThread.quit();
        mMarkerHandlerThread.quit();
        for (Marker marker : mAddMarkers) {
            marker.remove();

        }
        mAddMarkers.clear();
        mLruCache.evictAll();
    }

    /**
     * @param clusterMeta
     */
    public void setClusterMeta(ClusterMeta clusterMeta) {
        ClusterConsts.pxInMeters = aMap.getScalePerPixel();
        ClusterConsts.clusterDistance = ClusterConsts.clusterSize * ClusterConsts.pxInMeters;
        this.clusterMeta = clusterMeta;
        assignClusters();
    }

    /**
     * 添加点聚合
     *
     * @param clusters
     */
    public void addClusters(List<Cluster> clusters) {
        this.clusterMeta.addClusters(clusters);
        assignClusters();
    }

    /**
     * 添加点聚合
     *
     * @param cluster
     */
    public void addCluster(Cluster cluster) {
        this.clusterMeta.addCluster(cluster);
        assignClusters();
    }

    /**
     * 移除指定点聚合
     */
    public void removeCluster(List<Cluster> clusters) {
        this.clusterMeta.removeClusters(clusters);
        assignClusters();
    }

    /**
     * 移除指定点聚合
     */
    public void removeCluster(Cluster cluster) {
        this.clusterMeta.removeCluster(cluster);
        assignClusters();
    }

    /**
     * 移除所有点聚合
     */
    public void removeCluster() {
        this.clusterMeta.clearClusters();
        assignClusters();
    }


    //初始化Handler
    private void initThreadHandler() {
        mMarkerHandlerThread.start();
        mSignClusterThread.start();
        mMarkerHandler = new MarkerHandler(mMarkerHandlerThread.getLooper());
        mSignClusterHandler = new SignClusterHandler(mSignClusterThread.getLooper());
    }

    /**
     * 对点进行聚合
     */
    private void assignClusters() {
        ClusterConsts.isCanceled = true;
        mSignClusterHandler.removeMessages(SignClusterHandler.CALCULATE_CLUSTER);
        mSignClusterHandler.sendEmptyMessage(SignClusterHandler.CALCULATE_CLUSTER);
    }


    //-----------------------实现的Listener---------------------------------------------

    @Override
    public void onCameraChange(CameraPosition cameraPosition) {

    }

    @Override
    public void onCameraChangeFinish(CameraPosition cameraPosition) {
        if (clusterMeta.canCluster()) {
            ClusterConsts.pxInMeters = aMap.getScalePerPixel();
            ClusterConsts.clusterDistance = ClusterConsts.pxInMeters * ClusterConsts.clusterSize;
            assignClusters();
        }
    }

    //-----------------------辅助内部类用---------------------------------------------

    private AlphaAnimation mADDAnimation = new AlphaAnimation(0, 1);

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
                    List<Cluster> clusters = (List<Cluster>) message.obj;
                    addClusterToMap(clusters);
                    break;
                case ADD_SINGLE_CLUSTER:
                    //ClusterV2 cluster = (ClusterV2) message.obj;
                    //addSingleClusterToMap(cluster);
                    break;
                case UPDATE_SINGLE_CLUSTER:
                    //ClusterV2 updateCluster = (ClusterV2) message.obj;
                    //updateCluster(updateCluster);
                    break;
            }
        }
    }

    private List<Marker> mAddMarkers = new ArrayList<Marker>(); //地图真正的点

    /**
     * 将聚合元素添加至地图上
     */
    private void addClusterToMap(List<Cluster> clusters) {

        ArrayList<Marker> removeMarkers = new ArrayList<>();
        removeMarkers.addAll(mAddMarkers);
        AlphaAnimation alphaAnimation = new AlphaAnimation(1, 0);
        MyAnimationListener myAnimationListener = new MyAnimationListener(removeMarkers);
        for (Marker marker : removeMarkers) {
            marker.setAnimation(alphaAnimation);
            marker.setAnimationListener(myAnimationListener);
            marker.startAnimation();
        }

        for (Cluster cluster : clusters) {
            addSingleClusterToMap(cluster);
        }
    }

    /**
     * 将单个聚合元素添加至地图显示
     *
     * @param cluster
     */
    private void addSingleClusterToMap(Cluster cluster) {
        LatLng latlng = cluster.getCenterLatLng();
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.anchor(0.5f, 0.5f)
                .icon(getBitmapDes(cluster)).position(latlng);
        Marker marker = aMap.addMarker(markerOptions);
        marker.setAnimation(mADDAnimation);
        marker.setObject(cluster);

        marker.startAnimation();
        cluster.setMarker(marker);
        mAddMarkers.add(marker);
    }

    /**
     * 获取每个聚合点的绘制样式
     */
    private BitmapDescriptor getBitmapDes(Cluster culster) {
        //int num = culster.getClusterCount();
        int zoom = (int) aMap.getCameraPosition().zoom;
        ClusterRender clusterRender = clusterMeta.getClusterRender();
        int style = clusterRender.getDrawAbleStyle(zoom);
        BitmapDescriptor bitmapDescriptor = mLruCache.get(clusterMeta.getType().toString() + style);
        if (bitmapDescriptor == null) {
            ImageView imageView = new ImageView(context);

            Drawable drawAble = clusterRender.getDrawAble(style);
            if (clusterRender != null && drawAble != null) {
                imageView.setBackgroundDrawable(drawAble);
            } else {
                throw new IllegalArgumentException("clusterRenter is Null");
            }
            bitmapDescriptor = BitmapDescriptorFactory.fromView(imageView);
            mLruCache.put(clusterMeta.getType().toString() + style, bitmapDescriptor);

        }
        return bitmapDescriptor;
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
                    //ClusterItemV2 item = (ClusterItemV2) message.obj;
                    //mClusterItems.add(item);
                    //calculateSingleCluster(item);
                    break;
            }
        }
    }

    private void calculateClusters() {
        ClusterConsts.isCanceled = false;

        LatLngBounds visibleBounds = aMap.getProjection().getVisibleRegion().latLngBounds;
        clusterMeta.getClusters().clear();
        for (ILocation position : clusterMeta.getLocations()) {
            if (ClusterConsts.isCanceled) {
                return;
            }
            LatLng latlng = position.getLocation();
            if (visibleBounds.contains(latlng)) {
                Cluster cluster = null;
                if (clusterMeta.canCluster()) {
                    cluster = getCluster(latlng, clusterMeta.getClusters());
                }

                if (cluster != null) {
                    cluster.addLocation(position);
                } else {
                    cluster = new Cluster(latlng);
                    cluster.addLocation(position);
                    clusterMeta.addCluster(cluster);
                }
            }
        }

        //复制一份数据，规避同步
        List<Cluster> clusters = new ArrayList<>();
        clusters.addAll(clusterMeta.getClusters());
        Message message = Message.obtain();
        message.what = MarkerHandler.ADD_CLUSTER_LIST;
        message.obj = clusters;
        if (ClusterConsts.isCanceled) {
            return;
        }
        mMarkerHandler.sendMessage(message);
    }

    /**
     * 根据一个点获取是否可以依附的聚合点，没有则返回null
     *
     * @param latLng
     * @return
     */
    private Cluster getCluster(LatLng latLng, List<Cluster> clusters) {
        for (Cluster cluster : clusters) {
            LatLng clusterCenterPoint = cluster.getCenterLatLng();
            double distance = AMapUtils.calculateLineDistance(latLng, clusterCenterPoint);
            if (distance < ClusterConsts.clusterDistance && aMap.getCameraPosition().zoom < 19) {
                return cluster;
            }
        }

        return null;
    }
    //-----------------------处理聚合点算法线程 END---------------------------------------------
}