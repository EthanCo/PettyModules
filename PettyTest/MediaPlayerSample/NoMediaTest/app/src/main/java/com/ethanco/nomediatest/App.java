package com.ethanco.nomediatest;

import android.app.Application;
import android.util.Log;

import java.io.File;

/**
 * Application
 *
 * @author EthanCo
 * @since 2017/4/20
 */
public class App extends Application {
    private static App instance;
    public static final String TAG = "Z-NoMediaTest";

    public static App getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;

        File nomedia = NomediaUtil.getNomedia(this);
        Log.i(TAG, "file.path:" + nomedia.getPath());
        Boolean success = NomediaUtil.makeDirs(nomedia.getPath());
        Log.i(TAG, "mkDirs result:" + success);
    }
}
