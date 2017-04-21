package com.lib.ftpserver;

import android.util.Log;

/**
 * 简易日志工具类
 */
class LogUtil {
    public static final boolean DEBUG = true;
    public static String tag = "Z-FtpServer";

    public static void v(String msg) {
        if (DEBUG) Log.v(tag, msg);
    }

    public static void d(String msg) {
        if (DEBUG) Log.d(tag, msg);
    }

    public static void i(String msg) {
        if (DEBUG) Log.i(tag, msg);
    }

    public static void w(String msg) {
        if (DEBUG) Log.w(tag, msg);
    }

    public static void e(String msg) {
        if (DEBUG) Log.e(tag, msg);
    }
}

