package com.heiko.amaptest.clusterv2;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.UiSettings;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.LatLngBounds;
import com.amap.api.maps.model.Marker;
import com.heiko.amaptest.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.heiko.amaptest.clusterv2.ClusterUtils.dp2px;

public class ClusterV2Activity extends AppCompatActivity implements ClusterRenderV2, AMap.OnMapLoadedListener, ClusterClickListenerV2, AMap.OnCameraChangeListener {

    private MapView mMapView;
    private AMap mAMap;
    private ClusterOverlayV2 mClusterOverlay;
    private int clusterRadius = 100; //聚合半径
    private int imgCacheSize = 80; //图片缓存数量
    private Map<Integer, Drawable> mBackDrawAbles = new HashMap<Integer, Drawable>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cluster);

        mMapView = findViewById(R.id.map);
        mMapView.onCreate(savedInstanceState);
        init();

        /*new Thread() {
            @Override
            public void run() {
                super.run();

                while (true) {
                    SystemClock.sleep(2000);
                    Log.i("Z-Test-点聚合", "地图缩放级别:" + mAMap.getCameraPosition().zoom);
                }
            }
        }.start();*/
    }

    private void init() {
        if (mAMap == null) {
            // 初始化地图
            mAMap = mMapView.getMap();
            mAMap.setOnMapLoadedListener(this);
            UiSettings settings = mAMap.getUiSettings();
            settings.setScaleControlsEnabled(true);
            mAMap.setOnCameraChangeListener(this);
            //点击可以动态添加点
            /*mAMap.setOnMapClickListener(new AMap.OnMapClickListener() {
                @Override
                public void onMapClick(LatLng latLng) {
                    Toast.makeText(ClusterV2Activity.this, "Click", Toast.LENGTH_SHORT).show();
                    double lat = Math.random() + 39.474923;
                    double lon = Math.random() + 116.027116;

                    LatLng latLng1 = new LatLng(lat, lon, false);
                    ParkItemV2 regionItem = new ParkItemV2(latLng1, "test");
                    mClusterOverlay.addClusterItem(regionItem);

                }
            });*/
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //销毁资源
        mClusterOverlay.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    public void onMapLoaded() {
        mClusterOverlay = new ClusterOverlayV2(getApplication(), mAMap, clusterRadius,imgCacheSize);
        mClusterOverlay.setClusterRenderer(ClusterV2Activity.this);
        mClusterOverlay.setOnClusterClickListener(ClusterV2Activity.this);
    }

    @Override
    public void onClick(Marker marker, List<ClusterItemV2> clusterItems) {

        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        for (ClusterItemV2 clusterItem : clusterItems) {
            builder.include(clusterItem.getPosition());
        }
        LatLngBounds latLngBounds = builder.build();
        mAMap.animateCamera(CameraUpdateFactory.newLatLngBounds(latLngBounds, 0));
    }

    @Override
    public Drawable getDrawAble(int clusterNum) {
        int radius = dp2px(getApplicationContext(), 80);
        if (clusterNum == 1) {
            Drawable bitmapDrawable = mBackDrawAbles.get(1);
            if (bitmapDrawable == null) {
                bitmapDrawable =
                        getApplication().getResources().getDrawable(
                                R.drawable.icon_openmap_mark);
                mBackDrawAbles.put(1, bitmapDrawable);
            }

            return bitmapDrawable;
        } else if (clusterNum < 5) {

            Drawable bitmapDrawable = mBackDrawAbles.get(2);
            if (bitmapDrawable == null) {
                bitmapDrawable = new BitmapDrawable(null, drawCircle(radius,
                        Color.RED)); //Color.argb(159, 210, 154, 6)
                mBackDrawAbles.put(2, bitmapDrawable);
            }

            return bitmapDrawable;
        } else if (clusterNum < 10) {
            Drawable bitmapDrawable = mBackDrawAbles.get(3);
            if (bitmapDrawable == null) {
                bitmapDrawable = new BitmapDrawable(null, drawCircle(radius,
                        Color.BLUE)); //Color.argb(199, 217, 114, 0)
                mBackDrawAbles.put(3, bitmapDrawable);
            }

            return bitmapDrawable;
        } else {
            Drawable bitmapDrawable = mBackDrawAbles.get(4);
            if (bitmapDrawable == null) {
                bitmapDrawable = new BitmapDrawable(null, drawCircle(radius,
                        Color.GREEN)); //Color.argb(235, 215, 66, 2)
                mBackDrawAbles.put(4, bitmapDrawable);
            }

            return bitmapDrawable;
        }
    }

    private Bitmap drawCircle(int radius, int color) {

        Bitmap bitmap = Bitmap.createBitmap(radius * 2, radius * 2,
                Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();
        RectF rectF = new RectF(0, 0, radius * 2, radius * 2);
        paint.setColor(color);
        canvas.drawArc(rectF, 0, 360, true, paint);
        return bitmap;
    }

    @Override
    public void onCameraChange(CameraPosition cameraPosition) {

    }

    @Override
    public void onCameraChangeFinish(CameraPosition cameraPosition) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_clusterv2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_add_parker) {
            //添加测试数据
            new Thread() {
                public void run() {
                    List<ClusterItemV2> items = new ArrayList<ClusterItemV2>();
                    //随机10000个点
                    for (int i = 0; i < 10000; i++) {

                        double lat = Math.random() + 39.474923;
                        double lon = Math.random() + 116.027116;

                        LatLng latLng = new LatLng(lat, lon, false);
                        ParkItemV2 parks = new ParkItemV2(latLng, "test" + i);
                        items.add(parks);
                    }
                    mClusterOverlay.clearClusterItems();
                    mClusterOverlay.addClusterItems(items);
                }
            }.start();
        }
        return super.onOptionsItemSelected(item);
    }
}
