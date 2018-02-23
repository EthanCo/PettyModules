package com.heiko.architecturetest.lifecycle;

import android.arch.lifecycle.DefaultLifecycleObserver;
import android.arch.lifecycle.LifecycleOwner;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

/**
 * 实现DefaultLifecycleObserver接口
 *
 * @author EthanCo
 * @since 2018/2/23
 */
//如果是DefaultLifecycleObserver，@OnLifecycleEvent注解无效，需要实现相应生命周期方法，如onStart、onStop
public class MyObserver implements DefaultLifecycleObserver {
    private Context context;
    private CallBack callBack;
    public static final String TAG = "Z-MyObserver";

    public MyObserver(Context context, CallBack callBack) {
        this.context = context;
        this.callBack = callBack;
    }

    @Override
    public void onStart(@NonNull LifecycleOwner owner) {
        Log.i(TAG, "onStart");
        callBack.start();
    }

    @Override
    public void onStop(@NonNull LifecycleOwner owner) {
        Log.i(TAG, "onStop");
        callBack.stop();
    }

    /*@OnLifecycleEvent(Lifecycle.Event.ON_START)
    void start() {
        Log.i(TAG, "Lifecycle.Event.ON_START");
        callBack.start();
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    void stop() {
        Log.i(TAG, "Lifecycle.Event.ON_STOP");
        callBack.stop();
    }*/
}
