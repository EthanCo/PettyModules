package com.lib.utils.app; /**
 * Copyright 2014 Zhenguo Jin (jinzhenguo1990@gmail.com)
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.view.Window;
import android.view.WindowManager;

import java.io.File;
import java.io.FileFilter;
import java.util.regex.Pattern;

/**
 * 手机硬件信息 工具类 (与运行状态无关的)
 *
 * @author zhenguo
 */
@TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
public class DeviceUtil {

    /**
     * Don't let anyone instantiate this class.
     */
    private DeviceUtil() {
        throw new Error("Do not need instantiate!");
    }

    /**
     * 得到CPU核心数
     *
     * @return CPU核心数
     */
    public static int getNumCores() {
        try {
            File dir = new File("/sys/devices/system/cpu/");
            File[] files = dir.listFiles(new FileFilter() {
                @Override
                public boolean accept(File pathname) {
                    if (Pattern.matches("cpu[0-9]", pathname.getName())) {
                        return true;
                    }
                    return false;
                }
            });
            return files.length;
        } catch (Exception e) {
            return 1;
        }
    }

    /**
     * 获取手机型号
     *
     * @param context  上下文
     * @return String
     */
    public static String getMobileModel(Context context) {
        try {
            String model = android.os.Build.MODEL; // 手机型号
            return model;
        } catch (Exception e) {
            return "未知";
        }
    }

    /**
     * 获取手机品牌
     *
     * @param context  上下文
     * @return String
     */
    public static String getMobileBrand(Context context) {
        try {
            String brand = android.os.Build.BRAND; // android系统版本号
            return brand;
        } catch (Exception e) {
            return "未知";
        }
    }

    /**
     * 获取手机号码
     * @param context 上下文
     * @return 手机号码，手机号码不一定能获取到
     */
    public static String getMobilePhoneNumber(Context context) {
        return ((TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE)).getLine1Number();
    }

    /**
     * 获取系统屏幕亮度模式的状态，需要WRITE_SETTINGS权限
     *
     * @param context
     *            上下文
     * @return System.SCREEN_BRIGHTNESS_MODE_AUTOMATIC：自动；System.
     *         SCREEN_BRIGHTNESS_MODE_AUTOMATIC
     *         ：手动；默认：System.SCREEN_BRIGHTNESS_MODE_AUTOMATIC
     */
    public static int getScreenBrightnessModeState(Context context) {
        return Settings.System.getInt(context.getContentResolver(),
                Settings.System.SCREEN_BRIGHTNESS_MODE,
                Settings.System.SCREEN_BRIGHTNESS_MODE_AUTOMATIC);
    }

    /**
     * 判断系统屏幕亮度模式是否是自动，需要WRITE_SETTINGS权限
     *
     * @param context
     *            上下文
     * @return true：自动；false：手动；默认：true
     */
    public static boolean isScreenBrightnessModeAuto(Context context) {
        return getScreenBrightnessModeState(context) == Settings.System.SCREEN_BRIGHTNESS_MODE_AUTOMATIC ? true
                : false;
    }

    /**
     * 设置系统屏幕亮度模式，需要WRITE_SETTINGS权限
     *
     * @param context
     *            上下文
     * @param auto
     *            自动
     * @return 是否设置成功
     */
    public static boolean setScreenBrightnessMode(Context context, boolean auto) {
        boolean result = true;
        if (isScreenBrightnessModeAuto(context) != auto) {
            result = Settings.System.putInt(context.getContentResolver(),
                    Settings.System.SCREEN_BRIGHTNESS_MODE,
                    auto ? Settings.System.SCREEN_BRIGHTNESS_MODE_AUTOMATIC
                            : Settings.System.SCREEN_BRIGHTNESS_MODE_MANUAL);
        }
        return result;
    }

    /**
     * 获取系统亮度，需要WRITE_SETTINGS权限
     *
     * @param context
     *            上下文
     * @return 亮度，范围是0-255；默认255
     */
    public static int getScreenBrightness(Context context) {
        return Settings.System.getInt(context.getContentResolver(),
                Settings.System.SCREEN_BRIGHTNESS, 255);
    }

    /**
     * 设置系统亮度（此方法只是更改了系统的亮度属性，并不能看到效果。要想看到效果可以使用setWindowBrightness()方法设置窗口的亮度），
     * 需要WRITE_SETTINGS权限
     *
     * @param context
     *            上下文
     * @param screenBrightness
     *            亮度，范围是0-255
     * @return 设置是否成功
     */
    public static boolean setScreenBrightness(Context context,
                                              int screenBrightness) {
        int brightness = screenBrightness;
        if (screenBrightness < 1) {
            brightness = 1;
        } else if (screenBrightness > 255) {
            brightness = screenBrightness % 255;
            if (brightness == 0) {
                brightness = 255;
            }
        }
        boolean result = Settings.System.putInt(context.getContentResolver(),
                Settings.System.SCREEN_BRIGHTNESS, brightness);
        return result;
    }

    /**
     * 设置给定Activity的窗口的亮度（可以看到效果，但系统的亮度属性不会改变）
     *
     * @param activity
     *            要通过此Activity来设置窗口的亮度
     * @param screenBrightness
     *            亮度，范围是0-255
     */
    public static void setWindowBrightness(Activity activity,
                                           float screenBrightness) {
        float brightness = screenBrightness;
        if (screenBrightness < 1) {
            brightness = 1;
        } else if (screenBrightness > 255) {
            brightness = screenBrightness % 255;
            if (brightness == 0) {
                brightness = 255;
            }
        }
        Window window = activity.getWindow();
        WindowManager.LayoutParams localLayoutParams = window.getAttributes();
        localLayoutParams.screenBrightness = (float) brightness / 255;
        window.setAttributes(localLayoutParams);
    }

    /**
     * 设置系统亮度并实时可以看到效果，需要WRITE_SETTINGS权限
     *
     * @param activity
     *            要通过此Activity来设置窗口的亮度
     * @param screenBrightness
     *            亮度，范围是0-255
     * @return 设置是否成功
     */
    public static boolean setScreenBrightnessAndApply(Activity activity,
                                                      int screenBrightness) {
        boolean result = true;
        result = setScreenBrightness(activity, screenBrightness);
        if (result) {
            setWindowBrightness(activity, screenBrightness);
        }
        return result;
    }

    /**
     * 获取屏幕休眠时间，需要WRITE_SETTINGS权限
     *
     * @param context
     *            上下文
     * @return 屏幕休眠时间，单位毫秒，默认30000
     */
    public static int getScreenDormantTime(Context context) {
        return Settings.System.getInt(context.getContentResolver(),
                Settings.System.SCREEN_OFF_TIMEOUT, 30000);
    }

    /**
     * 设置屏幕休眠时间，需要WRITE_SETTINGS权限
     *
     * @param context
     *            上下文
     * @param millis    时间
     * @return 设置是否成功
     */
    public static boolean setScreenDormantTime(Context context, int millis) {
        return Settings.System.putInt(context.getContentResolver(),
                Settings.System.SCREEN_OFF_TIMEOUT, millis);

    }

    /**
     * 获取铃声音量，需要WRITE_APN_SETTINGS权限
     *
     * @param context
     *            上下文
     * @return 铃声音量，取值范围为0-7；默认为0
     */
    public static int getRingVolume(Context context) {
        return ((AudioManager) context.getSystemService(Context.AUDIO_SERVICE)).getStreamVolume(AudioManager.STREAM_RING);
    }

    /**
     * 获取媒体音量
     *
     * @param context
     *            上下文
     * @param ringVloume 音量
     */
    public static void setRingVolume(Context context, int ringVloume) {
        if (ringVloume < 0) {
            ringVloume = 0;
        } else if (ringVloume > 7) {
            ringVloume = ringVloume % 7;
            if (ringVloume == 0) {
                ringVloume = 7;
            }
        }

        ((AudioManager) context.getSystemService(Context.AUDIO_SERVICE)).setStreamVolume(AudioManager.STREAM_RING,
                ringVloume, AudioManager.FLAG_PLAY_SOUND);
    }

    /**
     * Mac地址
     * @param context
     * @return
     */
    public String getLocalMac(Context context) {
        String mac = "";
        // 获取wifi管理器
        WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        WifiInfo wifiInfor = wifiManager.getConnectionInfo();
        mac = wifiInfor.getMacAddress();
        return mac;
    }

    /**
     * 获取屏幕 宽
     * @return
     */
    public static int getWidth(Context context){
        WindowManager wm=(WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.widthPixels;
    }

    /**
     * 获取屏幕 高
     * @return
     */
    public static int getHeight(Context context){
        WindowManager wm=(WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.heightPixels;
    }
}