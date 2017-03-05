/**
 * *******************************************************************
 * AUTHOR：YOLANDA
 * DATE：2015年4月8日下午4:41:56
 * Copyright © 56iq. All Rights Reserved
 * ======================================================================
 * EDIT HISTORY
 * ----------------------------------------------------------------------
 * |  DATE      | NAME       | REASON       | CHANGE REQ.
 * ----------------------------------------------------------------------
 * | 2015年4月8日    | YOLANDA    | Created      |
 * <p>
 * DESCRIPTION：create the File, and add the content.
 * <p>
 * *********************************************************************
 */
package com.ethanco.simpleframe.frame.compat;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.Resources.Theme;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Build.VERSION;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;

/**
 * Description 获取资源兼容
 *
 * @Time 2015年4月8日 下午4:41:56
 */
public class ResourcesCompat {

    public static Drawable getDrawable(Context context, int resId) {
        return getDrawable(context, resId, null);
    }

    @SuppressWarnings("deprecation")
    @SuppressLint("NewApi")
    public static Drawable getDrawable(Context context, int resId, Theme theme) {
        Resources resources = context.getResources();
        if (VERSION.SDK_INT >= 21) {
            return resources.getDrawable(resId, theme);//heigh than leve21
        } else {
            return resources.getDrawable(resId);//small than leve21
        }
    }

    @ColorInt
    public static int getColor(Context context,@ColorRes int color){
        if (VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return context.getResources().getColor(color,null);
        }else{
            return context.getResources().getColor(color);
        }
    }
}
