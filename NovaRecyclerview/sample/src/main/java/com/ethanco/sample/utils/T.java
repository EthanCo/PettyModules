package com.ethanco.sample.utils;

import android.support.design.widget.Snackbar;
import android.view.View;

/**
 * Toast
 */
public class T {

    private T() {
    }

    public static void show(View view, int resId) {
        Snackbar.make(view, resId, Snackbar.LENGTH_LONG).show();
    }

    public static void show(View view, CharSequence text) {
        Snackbar.make(view, text, Snackbar.LENGTH_LONG).show();
    }

    public static void show(View view, int resId, int actionResId, View.OnClickListener listener) {
        Snackbar.make(view, resId, Snackbar.LENGTH_LONG).setAction(actionResId, listener).show();
    }

    public static void show(View view, CharSequence text, CharSequence action, View.OnClickListener listener) {
        Snackbar.make(view, text, Snackbar.LENGTH_LONG).setAction(action, listener).show();
    }
}