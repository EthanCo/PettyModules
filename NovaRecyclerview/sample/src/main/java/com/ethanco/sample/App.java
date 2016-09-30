package com.ethanco.sample;

import android.app.Application;

import com.ethanco.sample.utils.T;

/**
 * Created by EthanCo on 2016/9/28.
 */

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        T.init(this);
    }
}
