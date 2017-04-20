package com.lib.utils.compat;

import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.View;

/**
 * Description  ImageView设置背景的兼容
 * Created by chenqiao on 2015/7/27.
 */
public class BackgroundCompat {

    public static void setBackground(View view, Drawable drawable) {
        if (Build.VERSION.SDK_INT < 16) {
            view.setBackgroundDrawable(drawable);
        } else {
            view.setBackground(drawable);
        }
    }
}
