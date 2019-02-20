package com.heiko.amaptest.anchor;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.MyLocationStyle;
import com.heiko.amaptest.R;

public class AnchorActivity extends AppCompatActivity implements AMap.OnMapLoadedListener {
    static final float DEFAULT_MAP_ZOOM = 17.0f; // 地图缩放默认等级
    private MapView mapView;
    private AMap aMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anchor);

        mapView = (MapView) findViewById(R.id.map);
        //在activity执行onCreate时执行mMapView.onCreate(savedInstanceState)，创建地图
        mapView.onCreate(savedInstanceState);

        aMap = mapView.getMap();
        aMap.setMyLocationStyle(new MyLocationStyle());//设置定位蓝点的Style
        aMap.setMyLocationEnabled(true);// 设置为true表示启动显示定位蓝点，false表示隐藏定位蓝点并不进行定位，默认是false。
        aMap.moveCamera(CameraUpdateFactory.zoomTo(DEFAULT_MAP_ZOOM));  //设置缩放级别
        aMap.setOnMapLoadedListener(this);



        aMap.setInfoWindowAdapter(new AMap.InfoWindowAdapter() {
            View infoWindow = null;

            /**
             * 监听自定义infowindow窗口的infowindow事件回调
             */
            public View getInfoWindow(Marker marker) {
                if(infoWindow == null) {
                    infoWindow = LayoutInflater.from(AnchorActivity.this).inflate(
                            R.layout.custom_info_window, null);
                }
                render(marker, infoWindow);
                return infoWindow;
                //加载custom_info_window.xml布局文件作为InfoWindow的样式
                //该布局可在官方Demo布局文件夹下找到
            }

            /**
             * 自定义infowinfow窗口
             */
            public void render(Marker marker, View view) {
                //如果想修改自定义Infow中内容，请通过view找到它并修改
            }

            @Override
            public View getInfoContents(Marker marker) {
                return null;
            }
        });

        LatLng aMapCenterLatlng = new LatLng(29.858775, 121.601885);
        addMarker(aMapCenterLatlng,true);
    }

    @Override
    public void onMapLoaded() {
        Toast.makeText(AnchorActivity.this, "onMapLoaded", Toast.LENGTH_SHORT).show();
    }

    private void addMarker(LatLng aMapCenterLatlng, boolean isSnippet) {
        MarkerOptions markerOption = new MarkerOptions();
        markerOption.position(aMapCenterLatlng);
        if (isSnippet) {
            markerOption.title("宁波市").snippet("宁波市：29.858775, 121.601885");
        }

        markerOption.draggable(true);//设置Marker可拖动
        markerOption.icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory
                .decodeResource(getResources(), R.mipmap.ic_launcher)));
        // 将Marker设置为贴地显示，可以双指下拉地图查看效果
        markerOption.setFlat(true);//设置marker平贴地图效果
        Marker marker = aMap.addMarker(markerOption);
        marker.showInfoWindow();
    }
}
