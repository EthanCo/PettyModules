package com.ethanco.simpleframe.frame.compat;

import android.content.Context;
import android.os.Environment;

/**
 * Description 路径的兼容
 * Created by Zhk on 2015/12/24.
 */
public class DirCompat {
    public static String getCacheDir(Context context) {
        String path;
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            path = context.getExternalCacheDir().getPath();
        } else {
            path = context.getCacheDir().getPath();
        }
        return path;
    }
}
