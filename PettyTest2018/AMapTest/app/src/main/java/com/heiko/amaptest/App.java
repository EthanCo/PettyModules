package com.heiko.amaptest;

import android.app.Application;

/**
 * TODO
 *
 * @author Heiko
 * @date 2019/2/3
 */
public class App extends Application{
    private static Application instance;

    public static Application getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }
}
