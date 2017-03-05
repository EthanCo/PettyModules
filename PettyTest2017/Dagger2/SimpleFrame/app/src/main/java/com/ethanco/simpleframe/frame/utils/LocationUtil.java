package com.ethanco.simpleframe.frame.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;

/**
 * Description  定位工具类
 * 使用时请打开权限
 * <uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION"/>
 * <uses-permission android:name="android.permission.ACCESS_FINE_LOCATION"/>
 * Created by chenqiao on 2015/9/25.
 */
public class LocationUtil {

    private static LocationUtil instance;
    private static LocationManager locationManager;
    private Location myLocation = null;
    private LocationChangedListener locationChangedListener;

    private long time = 5000;
    private float distance = 5;

    /**
     * 封锁无参构造函数
     */
    private LocationUtil() {

    }

    private LocationUtil(Context context) {
        locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
    }

    /**
     * 定位需要使用单例模式获取实例
     *
     * @param context 上下文，建议传入ApplicationContext
     * @return 实例
     */
    public static LocationUtil getInstance(Context context) {
        if (instance == null) {
            synchronized (LocationUtil.class) {
                if (instance == null) {
                    instance = new LocationUtil(context);
                }
            }
        }
        return instance;
    }

    /**
     * 是否可以使用定位功能（只要GPS或网络其中一个开着，就是true）
     *
     * @param context 上下文
     * @return true or false
     */
    public static boolean isLocateEnable(Context context) {
        LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            return true;
        }
        return locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
    }

    /**
     * GPS是否打开
     *
     * @param context 上下文
     * @return true or false
     */
    public static boolean isGPSopen(Context context) {
        LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
    }

    /**
     * 跳转至GPS设置界面
     *
     * @param activity    跳转所用activity
     * @param requestCode 接收设置结果
     */
    public static void openGPSset(Activity activity, int requestCode) {
        Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
        activity.startActivityForResult(intent, requestCode); // 设置完成后返回到原来的界面
    }

    /**
     * 请求定位（使用GPS和网络同时定位，内部判断哪个更精确）
     *
     * @param clistener 定位结果回调
     */
    public void locate(LocationChangedListener clistener) {
        this.locationChangedListener = clistener;
        //  每隔5秒请求定位一次，移动5米回调一次
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, time, distance, listener);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, time, distance, listener);
    }

    /**
     * 设置定位时间间隔和距离
     *
     * @param time     每隔time进行一次定位（ms）
     * @param distance 每移动distance进行回调（m）
     */
    public void setTimeAndDistance(long time, float distance) {
        this.time = time;
        this.distance = distance;
    }

    /**
     * 停止定位，并移除监听器
     */
    public void stopLocate() {
        locationManager.removeUpdates(listener);
        this.locationChangedListener = null;
    }

    private LocationListener listener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            if (isBetterLocation(location, myLocation)) {
                myLocation = location;
            }
            if (locationChangedListener != null) {
                locationChangedListener.onChanged(myLocation);
            }
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    };

    private static final int TWO_MINUTES = 1000 * 2;

    /**
     * Determines whether one Location reading is better than the current Location fix
     *
     * @param location            The new Location that you want to evaluate
     * @param currentBestLocation The current Location fix, to which you want to compare the new one
     */
    protected boolean isBetterLocation(Location location, Location currentBestLocation) {
        if (currentBestLocation == null) {
            // A new location is always better than no location
            return true;
        }

        // Check whether the new location fix is newer or older
        long timeDelta = location.getTime() - currentBestLocation.getTime();
        boolean isSignificantlyNewer = timeDelta > TWO_MINUTES;
        boolean isSignificantlyOlder = timeDelta < -TWO_MINUTES;
        boolean isNewer = timeDelta > 0;

        // If it's been more than two minutes since the current location, use
        // the new location
        // because the user has likely moved
        if (isSignificantlyNewer) {
            return true;
            // If the new location is more than two minutes older, it must be
            // worse
        } else if (isSignificantlyOlder) {
            return false;
        }

        // Check whether the new location fix is more or less accurate
        int accuracyDelta = (int) (location.getAccuracy() - currentBestLocation.getAccuracy());
        boolean isLessAccurate = accuracyDelta > 0;
        boolean isMoreAccurate = accuracyDelta < 0;
        boolean isSignificantlyLessAccurate = accuracyDelta > 200;

        // Check if the old and new location are from the same provider
        boolean isFromSameProvider = isSameProvider(location.getProvider(), currentBestLocation.getProvider());

        // Determine location quality using a combination of timeliness and
        // accuracy
        if (isMoreAccurate) {
            return true;
        } else if (isNewer && !isLessAccurate) {
            return true;
        } else if (isNewer && !isSignificantlyLessAccurate && isFromSameProvider) {
            return true;
        }
        return false;
    }

    /**
     * Checks whether two providers are the same
     */
    private boolean isSameProvider(String provider1, String provider2) {
        if (provider1 == null) {
            return provider2 == null;
        }
        return provider1.equals(provider2);
    }

    public interface LocationChangedListener {
        void onChanged(Location nowLocation);
    }
}
