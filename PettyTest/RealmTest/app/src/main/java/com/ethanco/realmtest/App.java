package com.ethanco.realmtest;

import android.app.Application;

import io.realm.Realm;

/**
 * App
 *
 * @author EthanCo
 * @since 2016/12/5
 */

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
    }
}
