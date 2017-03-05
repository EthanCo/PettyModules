package com.ethanco.simpleframe.dagger.test;

import com.ethanco.simpleframe.temp.MainActivity;

import dagger.Component;

/**
 * Created by YOLANDA on 2016-01-11.
 */
@Component(modules = MainActivityModule.class)
public interface MainActivityComponent {
    void inject(MainActivity activity);
}
