package com.ethanco.mytest306;

import com.ethanco.mytest306.mvvm.viewmodel.MainViewModel;

import dagger.Component;

/**
 * Created by EthanCo on 2016/4/6.
 */
@Component(modules = ModelModule.class)
public interface ModelComponent {
    void inject(MainViewModel viewModel);
}
