package com.heiko.architecturetest.lifecycle;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;
import android.content.Context;
import android.util.Log;

/**
 * 实现LifecycleObserver接口
 *
 * @author EthanCo
 * @since 2018/2/23
 */

//实现LifecycleObserver，onStart、onStop等生命周期方法无效，需要使用@OnLifecycleEvent注解
public class TestObserver implements LifecycleObserver {
    private Context context;
    private CallBack callBack;
    public static final String TAG = "Z-TestObserver";

    public TestObserver(Context context, CallBack callBack) {
        this.context = context;
        this.callBack = callBack;
    }

   /* @Override
    public void onStart(@NonNull LifecycleOwner owner) {
        Log.i(TAG, "onStart");
        callBack.start();
    }

    @Override
    public void onStop(@NonNull LifecycleOwner owner) {
        Log.i(TAG, "onStop");
        callBack.stop();
    }*/

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    void start() {
        Log.i(TAG, "Lifecycle.Event.ON_START");
        callBack.start();
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    void stop() {
        Log.i(TAG, "Lifecycle.Event.ON_STOP");
        callBack.stop();
    }
}
