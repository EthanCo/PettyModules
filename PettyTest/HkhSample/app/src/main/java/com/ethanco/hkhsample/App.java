package com.ethanco.hkhsample;

import android.app.Application;

import com.lib.hkh.Hkh;

/**
 * @author EthanCo
 * @since 2016/11/23
 */

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        try {
            Hkh.init(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
