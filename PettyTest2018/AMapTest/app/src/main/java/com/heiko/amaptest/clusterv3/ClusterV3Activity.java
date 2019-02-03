package com.heiko.amaptest.clusterv3;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.UiSettings;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.heiko.amaptest.R;
import com.heiko.amaptest.clusterv3.bean.BikeInfo;

import java.util.ArrayList;
import java.util.List;

public class ClusterV3Activity extends AppCompatActivity implements AMap.OnMapLoadedListener, AMap.OnCameraChangeListener, AMap.OnMarkerClickListener {

    private MapView mMapView;
    private AMap mAMap;
    private ClusterOverlay mClusterOverlay;
    private int clusterRadius = 100; //聚合半径
    private int imgCacheSize = 80; //图片缓存数量

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cluster);

        mMapView = findViewById(R.id.map);
        mMapView.onCreate(savedInstanceState);
        init();
    }

    private void init() {
        if (mAMap == null) {
            // 初始化地图
            mAMap = mMapView.getMap();
            mAMap.setOnMapLoadedListener(this);
            UiSettings settings = mAMap.getUiSettings();
            settings.setScaleControlsEnabled(true);
            mAMap.setOnCameraChangeListener(this);
            mAMap.setOnMarkerClickListener(this);
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
        mClusterOverlay = new ClusterOverlay(getApplication(), mAMap, null, clusterRadius, imgCacheSize);
        //mClusterOverlay.setClusterRenderer(ClusterV3Activity.this);
        //mClusterOverlay.setOnClusterClickListener(ClusterV3Activity.this);
    }

    @Override
    public void onCameraChange(CameraPosition cameraPosition) {
        mClusterOverlay.onCameraChange(cameraPosition);
    }

    @Override
    public void onCameraChangeFinish(CameraPosition cameraPosition) {
        mClusterOverlay.onCameraChangeFinish(cameraPosition);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_clusterv2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_add_bike) {
            //添加测试数据
            new Thread() {
                public void run() {
                    List<ILocation> items = new ArrayList<>();
                    //随机10000个点
                    for (int i = 0; i < 10000; i++) {

                        double lat = Math.random() + 39.474923;
                        double lon = Math.random() + 116.027116;

                        LatLng latLng = new LatLng(lat, lon, false);
                        //ParkItemV2 parks = new ParkItemV2(latLng, "test" + i);
                        BikeInfo bikes = new BikeInfo(latLng);
                        items.add(bikes);
                    }
                    ClusterMeta clusterMeata = ClusterFactory.createClusterMeta(Type.BIKE, items);
                    mClusterOverlay.setClusterMeta(clusterMeata);
                }
            }.start();
        } else if (item.getItemId() == R.id.menu_add_parker) {
            //添加测试数据
            new Thread() {
                public void run() {
                    List<ILocation> items = new ArrayList<>();
                    //随机10000个点
                    for (int i = 0; i < 10000; i++) {

                        double lat = Math.random() + 39.474923;
                        double lon = Math.random() + 116.027116;

                        LatLng latLng = new LatLng(lat, lon, false);
                        //ParkItemV2 parks = new ParkItemV2(latLng, "test" + i);
                        BikeInfo bikes = new BikeInfo(latLng);
                        items.add(bikes);
                    }
                    ClusterMeta clusterMeata = ClusterFactory.createClusterMeta(Type.PARK, items);
                    mClusterOverlay.setClusterMeta(clusterMeata);
                }
            }.start();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        ClusterMeta clusterMeta = mClusterOverlay.getClusterMeta();
        Cluster cluster = (Cluster) marker.getObject();
        if (clusterMeta.canCluster()) {
            /*LatLngBounds.Builder builder = new LatLngBounds.Builder();
            for (ILocation position : cluster.getLocations()) {
                builder.include(position.getLocation());
            }
            LatLngBounds latLngBounds = builder.build();
            CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngBounds(latLngBounds, 0);
            //;cameraUpdate.getCameraUpdateFactoryDelegate().zoom;
            mAMap.animateCamera(cameraUpdate);
            ClusterConsts.limitZoom = mAMap.getCameraPosition().zoom;*/
            mAMap.animateCamera(CameraUpdateFactory.newLatLngZoom(cluster.getCenterLatLng(), 18));
        } else {
            Toast.makeText(ClusterV3Activity.this, cluster.getCenterLatLng().toString(), Toast.LENGTH_SHORT).show();
        }
        return true;
    }
}
