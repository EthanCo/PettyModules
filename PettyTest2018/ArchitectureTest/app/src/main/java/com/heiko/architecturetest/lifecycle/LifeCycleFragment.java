package com.heiko.architecturetest.lifecycle;

import android.app.Fragment;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LifecycleRegistry;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * 实现LifecycleOwner接口的Fragment
 *
 * @author EthanCo
 * @since 2018/2/23
 */
public class LifeCycleFragment extends Fragment implements LifecycleOwner {

    private LifecycleRegistry mLifecycleRegistry;

    public LifeCycleFragment() {
        mLifecycleRegistry = new LifecycleRegistry(this);
    }

    @NonNull
    @Override
    public Lifecycle getLifecycle() {
        return mLifecycleRegistry;
    }

    //注入生命周期
    /**
     * ON_CREATE,ON_START,ON_RESUME事件会在LifeCycleOwner对应的事件结束后回调，
     * ON_PAUSE,ON_STOP,ON_DESTROY会在LifeCycleOwner对应的函数执行之前回调
     * (PS:一定要注意回调顺序，回调顺序能保证你确定LifeCycleOwner当前所处的状态)。
     */
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mLifecycleRegistry = new LifecycleRegistry(this);
        mLifecycleRegistry.markState(Lifecycle.State.CREATED);
    }
    @Override
    public void onStart() {
        super.onStart();
        mLifecycleRegistry.markState(Lifecycle.State.STARTED);
    }
    @Override
    public void onResume() {
        super.onResume();
        mLifecycleRegistry.markState(Lifecycle.State.RESUMED);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mLifecycleRegistry.markState(Lifecycle.State.DESTROYED);
    }
}
