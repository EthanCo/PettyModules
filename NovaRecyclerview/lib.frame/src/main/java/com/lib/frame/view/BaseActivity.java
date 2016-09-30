package com.lib.frame.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.lib.frame.viewmodel.BaseViewModel;


/**
 * @param <V> View
 * @param <T> ViewModel
 * @Description Created by EthanCo on 2016/6/13.
 */
@SuppressWarnings("unchecked")
public abstract class BaseActivity<V, T extends BaseViewModel<V>> extends ToolbarActivity {
    private static final String TAG = "Z-BaseActivity";
    protected T mViewModel; //ViewModel对象
    private boolean isPrintLifeCycle = false; //是否打印Activity生命周期

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (isPrintLifeCycle) {
            Log.i(TAG, getClass().getSimpleName() + "onCreate : ");
        }
        mViewModel = createViewModel(); //创建ViewModel
        if (mViewModel != null) {
            mViewModel.attachView((V) this); //View与ViewModel建立关系
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (isPrintLifeCycle) {
            Log.i(TAG, getClass().getSimpleName() + "onStart : ");
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (isPrintLifeCycle) {
            Log.i(TAG, getClass().getSimpleName() + "onResume : ");
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (isPrintLifeCycle) {
            Log.i(TAG, getClass().getSimpleName() + "onPause : ");
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (isPrintLifeCycle) {
            Log.i(TAG, getClass().getSimpleName() + "onStop : ");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (isPrintLifeCycle) {
            Log.i(TAG, getClass().getSimpleName() + "onDestroy : ");
        }
        if (mViewModel != null) {
            mViewModel.detachView();
        }
    }

    /**
     * 创建ViewModel
     *
     * @return
     */
    protected abstract T createViewModel();
}
