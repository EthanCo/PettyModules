package com.ethanco.baidulocationsample;


import android.app.Application;
import android.content.Intent;

import com.ethanco.lib.baidu.BaiduFacede;

/**
 * Application
 *
 * @author EthanCo
 * @since 2016/12/19
 */

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        BaiduFacede.init(this);

        Intent intent = new Intent(this, TestService.class);
        startService(intent);
    }
}
