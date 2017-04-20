package com.lib.utils.print;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.Toast;

/**
 * Toast
 * 1.无需考虑是否是UI线程
 * 2.show()可不带context对象
 */
public class T {

    private T() {
        throw new UnsupportedOperationException("Cannot be instantiated");
    }

    private static Application context;

    public static boolean isAvailable() {
        return context == null;
    }

    public static void init(Application application) {
        if (context == null) {
            context = application;
        }
    }

    public static void show(int resId) {
        show(context, resId, Toast.LENGTH_SHORT);
    }

    public static void show(CharSequence text) {
        show(context, text, Toast.LENGTH_SHORT);
    }

    public static void show(int resId, int duration) {
        show(context, resId, duration);
    }

    public static void show(CharSequence text, int duration) {
        show(context, text, duration);
    }

    public static void show(final Context context, final int resId, final int duration) {
        show(context, context.getString(resId), duration);
    }

    public static void show(final Context context, final CharSequence text, final int duration) {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(context, text, duration).show();
            }
        });
    }

    @Deprecated //Android4.4 平板不适合使用这个 (有点不兼容)
    public static void show(View view, int resId) {
        Snackbar.make(view, resId, Snackbar.LENGTH_LONG).show();
    }

    @Deprecated
    public static void show(View view, CharSequence text) {
        Snackbar.make(view, text, Snackbar.LENGTH_LONG).show();
    }

    @Deprecated
    public static void show(View view, int resId, int actionResId, View.OnClickListener listener) {
        Snackbar.make(view, resId, Snackbar.LENGTH_LONG).setAction(actionResId, listener).show();
    }

    @Deprecated
    public static void show(View view, CharSequence text, CharSequence action, View.OnClickListener listener) {
        Snackbar.make(view, text, Snackbar.LENGTH_LONG).setAction(action, listener).show();
    }
}