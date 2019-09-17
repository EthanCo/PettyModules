package com.heiko.amaptest;

import android.animation.ValueAnimator;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewTreeObserver;
import android.view.animation.LinearInterpolator;

import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdate;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.Circle;
import com.amap.api.maps.model.CircleOptions;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.MyLocationStyle;
import com.amap.api.maps.model.Polygon;
import com.amap.api.maps.model.PolygonOptions;
import com.amap.api.maps.model.Polyline;
import com.amap.api.maps.model.PolylineOptions;
import com.amap.api.maps.model.animation.Animation;
import com.amap.api.maps.model.animation.ScaleAnimation;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AMap.OnMapLoadedListener {
    static final float DEFAULT_MAP_ZOOM = 17.0f; // 地图缩放默认等级
    MapView mapView = null;
    private AMap aMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //获取地图控件引用
        mapView = (MapView) findViewById(R.id.map);
        //在activity执行onCreate时执行mMapView.onCreate(savedInstanceState)，创建地图
        mapView.onCreate(savedInstanceState);

        aMap = mapView.getMap();
       /* aMap.moveCamera(CameraUpdateFactory.zoomTo(DEFAULT_MAP_ZOOM));  //设置缩放级别

        MyLocationStyle myLocationStyle = getLocationStyle();
        //myLocationStyle = new MyLocationStyle();//初始化定位蓝点样式类myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE);//连续定位、且将视角移动到地图中心点，定位点依照设备方向旋转，并且会跟随设备移动。（1秒1次定位）如果不设置myLocationType，默认也会执行此种模式。
        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_FOLLOW) ;//连续定位、且将视角移动到地图中心点，定位蓝点跟随设备移动。（1秒1次定位）
        //myLocationStyle.interval(2000); //设置连续定位模式下的定位间隔，只在连续定位模式下生效，单次定位模式下不会生效。单位为毫秒。
        aMap.setMyLocationStyle(myLocationStyle);//设置定位蓝点的Style
        //aMap.getUiSettings().setMyLocationButtonEnabled(true);//设置默认定位按钮是否显示，非必需设置。
        aMap.setMyLocationEnabled(true);// 设置为true表示启动显示定位蓝点，false表示隐藏定位蓝点并不进行定位，默认是false。*/

        aMap.moveCamera(CameraUpdateFactory.zoomTo(DEFAULT_MAP_ZOOM));  //设置缩放级别
        aMap.setMyLocationStyle(getLocationStyle());//设置定位蓝点的Style
        //aMap.getUiSettings().setMyLocationButtonEnabled(true);设置默认定位按钮是否显示，非必需设置。
        aMap.setMyLocationEnabled(true);// 设置为true表示启动显示定位蓝点，false表示隐藏定位蓝点并不进行定位，默认是false。
        aMap.setOnMapLoadedListener(this);
//        aMap.setOnMapClickListener(mAmapClickListener);
//        aMap.setOnMarkerClickListener(mAmapMarkerClickListener);

        LatLng aMapCenterLatlng = new LatLng(29.858775, 121.601885);
        aMap.stopAnimation();
        aMap.moveCamera(createCameraUpdate(aMapCenterLatlng));

        LatLng aMapCenterLatlng2 = new LatLng(29.855513, 121.600579);

        //绘制点标记
        addMarker(aMapCenterLatlng, true);
        addMarker(aMapCenterLatlng2, false);

        //绘制线
        List<LatLng> latLngs = new ArrayList<LatLng>();
        latLngs.add(new LatLng(29.858775, 121.601885));
        latLngs.add(new LatLng(29.855513, 121.600579));
        Polyline polyline = aMap.addPolyline(new PolylineOptions().
                addAll(latLngs).width(10).color(Color.argb(255, 1, 1, 1)));

        //绘制圆
        LatLng latLng = new LatLng(29.858775, 121.601885);
        Circle circle = aMap.addCircle(new CircleOptions().
                center(latLng).
                radius(100).
                fillColor(Color.argb(125, 255, 60, 125)).
                strokeColor(Color.argb(125, 255, 60, 125)).
                strokeWidth(15));

        //绘制椭圆
        PolygonOptions options = new PolygonOptions();
        int numPoints = 400;
        float semiHorizontalAxis = 0.002f;
        float semiVerticalAxis = 0.001f;
        double phase = 2 * Math.PI / numPoints;
        for (int i = 0; i <= numPoints; i++) {
            options.add(new LatLng(29.855513
                    + semiVerticalAxis * Math.sin(i * phase),
                    121.600579 + semiHorizontalAxis
                            * Math.cos(i * phase)));
        }
        Polygon polygon = aMap.addPolygon(options.strokeWidth(25)
                .strokeColor(Color.argb(50, 1, 1, 1))
                .fillColor(Color.argb(50, 1, 1, 1)));

        //绘制一个长方形
        aMap.addPolygon(new PolygonOptions()
                .addAll(createRectangle(new LatLng(29.857275, 121.601185), 0.0005f, 0.0005f))
                .fillColor(Color.LTGRAY).strokeColor(Color.RED).strokeWidth(1));

        //绘制不规则多边形
        // 定义多边形的5个点点坐标
        LatLng latLng1 = new LatLng(29.858775, 121.601885);
        LatLng latLng2 = new LatLng(29.859775, 121.601885);
        LatLng latLng3 = new LatLng(29.859775, 121.611885);
        LatLng latLng4 = new LatLng(29.859513, 121.605579);
        LatLng latLng5 = new LatLng(29.855513, 121.600579);
//        LatLng latLng1 = new LatLng(42.742467, 79.842785);
//        LatLng latLng2 = new LatLng(43.893433, 98.124035);
//        LatLng latLng3 = new LatLng(33.058738, 101.463879);
//        LatLng latLng4 = new LatLng(25.873426, 95.838879);
//        LatLng latLng5 = new LatLng(30.8214661, 78.788097);

        // 声明 多边形参数对象
        PolygonOptions polygonOptions = new PolygonOptions();
        // 添加 多边形的每个顶点（顺序添加）
        polygonOptions.add(latLng1, latLng2, latLng3, latLng4, latLng5);
        polygonOptions.strokeWidth(15) // 多边形的边框
                .strokeColor(Color.argb(50, 1, 1, 1)) // 边框颜色
                .fillColor(Color.argb(125, 60, 255, 125));   // 多边形的填充色
        aMap.addPolygon(polygonOptions);
    }

    /**
     * 生成一个长方形的四个坐标点
     */
    private List<LatLng> createRectangle(LatLng center, double halfWidth,
                                         double halfHeight) {
        List<LatLng> latLngs = new ArrayList<LatLng>();
        latLngs.add(new LatLng(center.latitude - halfHeight, center.longitude - halfWidth));
        latLngs.add(new LatLng(center.latitude - halfHeight, center.longitude + halfWidth));
        latLngs.add(new LatLng(center.latitude + halfHeight, center.longitude + halfWidth));
        latLngs.add(new LatLng(center.latitude + halfHeight, center.longitude - halfWidth));
        return latLngs;
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
        final Marker marker = aMap.addMarker(markerOption);

        Animation animation = new ScaleAnimation(1F,2F,1F,2F);
        long duration = 1000L;
        animation.setRepeatMode(Animation.INFINITE);
        animation.setDuration(duration);
        animation.setInterpolator(new LinearInterpolator());
        animation.setRepeatCount(1);
        animation.setRepeatMode(ValueAnimator.REVERSE);
        /*animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart() {

            }

            @Override
            public void onAnimationEnd() {
                Animation animation2 = new AlphaAnimation(0F,1F);
                animation2.setDuration(5000);
                animation2.setInterpolator(new LinearInterpolator());
                marker.setAnimation(animation2);
                marker.startAnimation();
            }
        });*/

        marker.setAnimation(animation);
        marker.startAnimation();
    }

    /**
     * SDK 5.1.0 设置地图中心点，双击缩放时中心点位置不变，但是捏合依然偏移 - -!
     */
    private void initMapCenterPoint() {
        mapView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
                    mapView.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                } else {
                    mapView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                }

                int pointX = mapView.getWidth() / 2;
                int pointY = mapView.getHeight() / 2;
                Log.i("Z-Test", "mapView pointX:" + pointX + " pointY:" + pointY);
                aMap.setPointToCenter(pointX, pointY);
            }
        });
    }

    protected CameraUpdate createCameraUpdate(LatLng latLng) {
        return CameraUpdateFactory.newCameraPosition(CameraPosition.fromLatLngZoom(latLng, DEFAULT_MAP_ZOOM));
    }

    /**
     * 自定义定位点样式
     *
     * @return
     */
    public MyLocationStyle getLocationStyle() {

        return new MyLocationStyle()//初始化定位蓝点样式类
                // 自定义定位蓝点图标
                .myLocationIcon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_launcher))
                // 自定义精度范围的圆形边框颜色
                .strokeColor(Color.RED) //0xc4d3e7
                // 自定义精度范围的圆形边框宽度
                .strokeWidth(10) //1
                // 设置圆形的填充颜色
                .radiusFillColor(Color.BLUE) //0x66c4d3e7
                //连续定位、且将视角移动到地图中心点，定位点依照设备方向旋转，并且会跟随设备移动。（1秒1次定位）如果不设置myLocationType，默认也会执行此种模式。
                .myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE_NO_CENTER);
        // .interval(DEFAULT_LOCATION_INTERVAL);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，销毁地图
        mapView.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView.onResume ()，重新绘制加载地图
        mapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView.onPause ()，暂停地图的绘制
        mapView.onPause();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState (outState)，保存地图当前的状态
        mapView.onSaveInstanceState(outState);
    }

    @Override
    public void onMapLoaded() {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //return super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //return super.onOptionsItemSelected(item);
        if (item.getItemId() == R.id.menu_test) {
            Intent intent = new Intent(this, TestActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
