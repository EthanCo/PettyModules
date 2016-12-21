package com.ethanco.alihotfixtest;

import android.app.Application;

import com.lib.alihotfix.AliHotFix;

/**
 * @author EthanCo
 * @since 2016/12/20
 */

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        AliHotFix.init(this, "83145-1");
    }
}
