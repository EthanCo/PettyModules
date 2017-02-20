package com.ethanco.aroutertest;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;

/**
 * App
 *
 * @author EthanCo
 * @since 2017/2/20
 */

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //初始化ARouter
        ARouter.init(this);
        //打印日志并打印堆栈
        ARouter.openLog();
        //开启调试模式
        ARouter.openDebug();

    }
}
