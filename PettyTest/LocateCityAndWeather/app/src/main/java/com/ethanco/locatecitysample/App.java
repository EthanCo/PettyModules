package com.ethanco.locatecitysample;

import android.app.Application;

import com.nbhope.hopelauncher.lib.network.NetFacade;

/**
 * @Description Application
 * Created by EthanCo on 2016/10/10.
 */

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        
        NetFacade.init(this);
    }
}
