package com.ethanco.simpleframe.dagger;

import com.ethanco.simpleframe.MyApplication;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by EthanCo on 2016/4/5.
 */
@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    void inject(MyApplication application);
}
