package com.ethanco.mytest306;

import com.ethanco.mytest306.mvvm.model.IMainModel;
import com.ethanco.mytest306.mvvm.model.MainModel;

import dagger.Module;
import dagger.Provides;

/**
 * Created by EthanCo on 2016/4/6.
 */
@Module
public class ModelModule {
    @Provides
    public IMainModel provideMainModel() {
        return new MainModel();
    }
}
