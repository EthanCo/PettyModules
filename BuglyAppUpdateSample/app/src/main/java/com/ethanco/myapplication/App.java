package com.ethanco.myapplication;

import android.app.Application;

import com.ethanco.lib.bugly.BuglyWrap;

/**
 * Created by EthanCo on 2016/11/9.
 */

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        BuglyWrap.initAndCustomDialog(getApplicationContext(), "7f525d2734", false);
    }
}
