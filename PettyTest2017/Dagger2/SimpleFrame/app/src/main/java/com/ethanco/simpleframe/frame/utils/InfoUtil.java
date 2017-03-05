package com.ethanco.simpleframe.frame.utils;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;

/**
 * Description  设备系统信息工具类
 * Created by chenqiao on 2015/11/5.
 */
public class InfoUtil {

    public static String getSystemVersion() {
        return Build.VERSION.RELEASE;
    }

    public static String getPhoneModel() {
        return Build.MODEL;
    }

    public static String getPhoneBrand() {
        return Build.BRAND;
    }

    public static String getSerialNumber() {
        return Build.SERIAL;
    }

    public static String getDisplay() {
        return Build.DISPLAY;
    }

    public static String getMacAddress(Context mContext) {
        WifiManager wifiManager = (WifiManager) mContext.getSystemService(Context.WIFI_SERVICE);
        WifiInfo wifiInfo = wifiManager.getConnectionInfo();
        if (wifiInfo.getMacAddress() != null) {
            return wifiInfo.getMacAddress();
        } else {
            return "";
        }
    }

    //IMEI
    public static String getDeviceId(Context mContext) {
        TelephonyManager tm = (TelephonyManager) mContext.getSystemService(Context.TELEPHONY_SERVICE);
        return tm.getDeviceId();
    }

    public static String getAndroidId(Context mContext) {
        String id = Settings.Secure.getString(mContext.getContentResolver(), Settings.Secure.ANDROID_ID);
        return id;
    }
}