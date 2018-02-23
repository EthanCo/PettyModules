package com.ethanco.mymaterialdialogtest;

import android.app.Application;

/**
 * Application
 *
 * @author EthanCo
 * @since 2018/2/22
 */

public class App extends Application{
    private static App instance;

    public static Application getInstance(){
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance  = this;
    }
}
