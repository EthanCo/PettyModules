package com.ethanco.mytest306.mvvm.viewmodel;

import com.ethanco.mytest306.App;
import com.ethanco.mytest306.ModelComponent;

/**
 * Created by EthanCo on 2016/4/6.
 */
public abstract class BaseViewModel {
    public BaseViewModel() {
        initInject(App.getInstance().getModelComponent());
    }

    protected abstract void initInject(ModelComponent build);
}
