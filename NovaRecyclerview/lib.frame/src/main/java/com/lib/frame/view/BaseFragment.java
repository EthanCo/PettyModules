package com.lib.frame.view;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lib.frame.viewmodel.BaseViewModel;

/**
 * @param <V> View
 * @param <T> ViewModel
 * @Description Fragment 基类
 * Created by EthanCo on 2016/6/13.
 */
@SuppressWarnings("unchecked")
public abstract class BaseFragment<V, T extends BaseViewModel<V>> extends Fragment {
    private String title;
    protected boolean isVisibleToUser;
    private static final String TAG = "Z-BaseFragment";
    private boolean isPrintLifeCycle = false; //是否打印Fragment生命周期
    protected T mViewModel; //ViewModel对象

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (isPrintLifeCycle) {
            Log.i(TAG, getClass().getSimpleName() + " - onAttach : ");
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (isPrintLifeCycle) {
            Log.i(TAG, getClass().getSimpleName() + " - onCreate : ");
        }
        mViewModel = createViewModel(); //创建ViewModel
        if (mViewModel != null) {
            mViewModel.attachView((V) this); //View与ViewModel建立关系
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (isPrintLifeCycle) {
            Log.i(TAG, getClass().getSimpleName() + " - onCreateView : ");
        }
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (isPrintLifeCycle) {
            Log.i(TAG, getClass().getSimpleName() + " - onActivityCreated : ");
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        if (isPrintLifeCycle) {
            Log.i(TAG, getClass().getSimpleName() + " - onStart : ");
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (isPrintLifeCycle) {
            Log.i(TAG, getClass().getSimpleName() + " - onResume : ");
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (isPrintLifeCycle) {
            Log.i(TAG, getClass().getSimpleName() + " - onPause : ");
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (isPrintLifeCycle) {
            Log.i(TAG, getClass().getSimpleName() + "-onStop : ");
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (isPrintLifeCycle) {
            Log.i(TAG, getClass().getSimpleName() + "-onDestroyView : ");
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (isPrintLifeCycle) {
            Log.i(TAG, getClass().getSimpleName() + "-onDestroy : ");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if (isPrintLifeCycle) {
            Log.i(TAG, getClass().getSimpleName() + "-onDetach : ");
        }
        if (mViewModel != null) {
            mViewModel.detachView();
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (isPrintLifeCycle) {
            Log.i(TAG, getClass().getSimpleName() + "-onHiddenChanged : " + hidden);
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        this.isVisibleToUser = isVisibleToUser;
        if (isPrintLifeCycle) {
            Log.i(TAG, getClass().getSimpleName() + "-isVisibleToUser : " + isVisibleToUser);
        }
    }

    /**
     * 创建ViewModel
     *
     * @return
     */
    protected abstract T createViewModel();
}
