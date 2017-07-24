package com.ethanco.lib.baidu;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;

/**
 * 百度地图 封装门面
 *
 * @author EthanCo
 * @since 2016/12/19
 */

public class BaiduFacede {

    private static LocationClient mLocationClient = null;
    private static BaiduLocationListener baiduLocationListener = null;
    private static BDLocation location = null;
    private static boolean isInited = false;

    /**
     * 初始化百度，只定位一次，立即启动
     *
     * @param context
     */
    public static void init(Context context) {
        init(context, true, true);
    }

    /**
     * 初始化百度APi
     *
     * @param context
     * @param isLocationOnce 是否只定位一次
     * @param isStartNow     是否立即启动，若为false需手动调用mLocationClient.start();才能定位
     */
    public static void init(Context context, boolean isLocationOnce, boolean isStartNow) {
        if (isNetworkAvailable(context)) {
            isInited = true;
            mLocationClient = new LocationClient(context);     //声明LocationClient类
            baiduLocationListener = new BaiduLocationListener(isLocationOnce);
            mLocationClient.registerLocationListener(baiduLocationListener);    //注册监听函数
            initLocation();

            if (isStartNow) {
                mLocationClient.start();
            }
        }
    }

    private static void initLocation() {
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);//可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
        option.setCoorType("bd09ll");//可选，默认gcj02，设置返回的定位结果坐标系
        int span = 1000;
        option.setScanSpan(span);//可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的
        option.setIsNeedAddress(true);//可选，设置是否需要地址信息，默认不需要
        option.setOpenGps(false);//可选，默认false,设置是否使用gps
        option.setLocationNotify(false);//可选，默认false，设置是否当GPS有效时按照1S/1次频率输出GPS结果
        option.setIsNeedLocationDescribe(false);//可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”
        option.setIsNeedLocationPoiList(false);//可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到
        option.setIgnoreKillProcess(false);//可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死
        option.SetIgnoreCacheException(false);//可选，默认false，设置是否收集CRASH信息，默认收集
        option.setEnableSimulateGps(false);//可选，默认false，设置是否需要过滤GPS仿真结果，默认需要
        mLocationClient.setLocOption(option);
    }

    public static LocationClient getmLocationClient() {
        return mLocationClient;
    }

    public static void addLocationListener(ILocationListener locationListener) {
        baiduLocationListener.addLocationListener(locationListener);
    }

    public static void removeLocationListener(ILocationListener locationListener) {
        baiduLocationListener.removeLocationListener(locationListener);
    }

    public static BDLocation getLocation() {
        return location;
    }

    static void setLocation(BDLocation location) {
        BaiduFacede.location = location;
    }

    public static void startLocate(Context context) {
        if (!isInited) init(context);

        if (mLocationClient != null) {
            mLocationClient.start();
        }
    }

    public static void stopLocate(Context context) {
        if (!isInited) init(context);

        if (mLocationClient != null) {
            mLocationClient.stop();
        }
    }

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo info = connectivity.getActiveNetworkInfo();
            if (info != null && info.isConnected()) {
                // 当前网络是连接的
                if (info.getState() == NetworkInfo.State.CONNECTED) {
                    // 当前所连接的网络可用
                    return true;
                }
            }
        }
        return false;
    }
}
