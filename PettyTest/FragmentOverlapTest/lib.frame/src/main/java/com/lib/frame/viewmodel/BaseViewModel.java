package com.lib.frame.viewmodel;

import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;

import com.lib.frame.injector.ZAnnoInjector;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

/**
 * @Description ViewModel基类
 * Created by EthanCo on 2016/6/13.
 */
public abstract class BaseViewModel<T> {

    protected Reference<T> mViewRef; //View接口类型的弱引用

    @CallSuper
    public void attachView(T view) { //建立关联
        mViewRef = new WeakReference<T>(view);
    }

    @NonNull
    protected T getView() {
        if (mViewRef == null) return null;

        T t = mViewRef.get();
        if (t == null) {

        }
        return t;
    }

    public boolean isViewAttached() {
        return mViewRef != null && mViewRef.get() != null;
    }

    @CallSuper
    public void detachView() {

        ZAnnoInjector.inject(this);

        if (mViewRef != null) {
            mViewRef.clear();
            mViewRef = null;
        }
    }
}
