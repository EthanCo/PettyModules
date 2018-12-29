package com.zhihu.matisse.sample;

import android.app.Application;

/**
 * TODO
 *
 * @author Heiko
 * @date 2018/12/28
 */
public class App extends Application {
    public static App app;

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
    }
}
