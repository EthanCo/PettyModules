package com.lib.frame.view;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lib.frame.view.abs.ICreate;
import com.lib.frame.viewmodel.BaseViewModel;

/**
 * @param <V> View
 * @param <T> ViewModel
 * @Description Fragment 基类
 * Created by EthanCo on 2016/6/13.
 */
@SuppressWarnings("unchecked")
public abstract class BaseFragment<V, T extends BaseViewModel<V>> extends ToolbarFragment implements ICreate{

    protected boolean isVisibleToUser;
    protected final String TAG = this.getClass().getSimpleName();
    private static final boolean IS_PRINT_LIFECYCLE = false; //是否打印Fragment生命周期
    protected T mViewModel; //ViewModel对象

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        printLifeCycle("onAttach : ");
    }



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        printLifeCycle("onCreate : ");
        mViewModel = createViewModel(); //创建ViewModel
        if (mViewModel != null) {
            mViewModel.attachView((V) this); //View与ViewModel建立关系
        }
    }

    //创建ViewModel
    protected abstract T createViewModel();

    //初始化变量和界面
    @Override
    public abstract View initVarAndView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);

    //用于继承自BaseActivity的基类 进行一些初始化，一般情况下，不用重写
    public void midfield() {
    }

    //初始化事件
    public void initEvent(){

    }

    //开始执行
    public void initDoing() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        printLifeCycle("onCreateView : ");
        Log.d("test", "onCreateView");
        View view = initVarAndView(inflater, container, savedInstanceState);
        midfield();
        initEvent();
        initDoing();
        return view == null ? super.onCreateView(inflater, container, savedInstanceState) : view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        printLifeCycle("onActivityCreated : ");
    }

    @Override
    public void onStart() {
        super.onStart();
        printLifeCycle("onStart : ");
    }

    @Override
    public void onResume() {
        super.onResume();
        printLifeCycle("onResume : ");
    }

    @Override
    public void onPause() {
        super.onPause();
        printLifeCycle("onPause : ");
    }

    @Override
    public void onStop() {
        super.onStop();
        printLifeCycle("onStop : ");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        printLifeCycle("onDestroyView : ");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        printLifeCycle("onDestroy : ");
    }

    @Override
    public void onDetach() {
        printLifeCycle("onDetach : ");
        if (mViewModel != null) {
            mViewModel.detachView();
        }
        super.onDetach();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        printLifeCycle("-onHiddenChanged : " + hidden);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        this.isVisibleToUser = isVisibleToUser;
        printLifeCycle("-isVisibleToUser : " + isVisibleToUser);
    }

    private void printLifeCycle(String msg) {
        if (IS_PRINT_LIFECYCLE) {
            Log.i(TAG, getClass().getSimpleName() + " - " + msg);
        }
    }
}
