package com.ethanco.splashscreenbest;

import android.app.Application;
import android.os.SystemClock;

/**
 * App
 *
 * @author EthanCo
 * @since 2017/5/8
 */

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        SystemClock.sleep(3000);
    }
}
