package com.ethanco.mytest306.mvvm.viewmodel;

import android.util.Log;

import com.ethanco.mytest306.BaseActivity;
import com.ethanco.mytest306.ModelComponent;
import com.ethanco.mytest306.mvvm.model.IMainModel;
import com.ethanco.mytest306.mvvm.view.IMainView;

import javax.inject.Inject;

/**
 * Created by EthanCo on 2016/4/6.
 */
public class MainViewModel extends BaseViewModel {

    private IMainView view;

    @Inject
    IMainModel model;

    public MainViewModel(BaseActivity baseActivity) {
        if (baseActivity instanceof IMainView) {
            this.view = (IMainView) baseActivity;
        } else {
            throw new IllegalArgumentException("activity类型错误");
        }
        Log.i("Z-MainViewModel", "MainViewModel : " + view.getClass().getName());
    }

    @Override
    protected void initInject(ModelComponent build) {
        build.inject(this);
    }
}
