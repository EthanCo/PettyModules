package com.nbhope.hopelauncher.lib.network.sbscribe.base;

import android.util.Log;

/**
 * Created by EthanCo on 2016/1/3.
 */
public class LogSubscriber<T> extends BaseSubscriber<T> {
    protected static final String TAG = "Z-Subscriber";
    protected static boolean isDebug = true;

    public static boolean isDebug() {
        return isDebug;
    }

    public static void setIsDebug(boolean isDebug) {
        LogSubscriber.isDebug = isDebug;
    }

    @Override
    public void onCompleted() {
        Log.i(TAG, "onCompleted");
    }

    @Override
    public void onError(Throwable e) {
        Log.i(TAG, "onError: " + e.getMessage());
    }

    @Override
    public void onNext(T t) {
        if (isDebug) {
            Log.i(TAG, "onNext");
        }
    }
}
