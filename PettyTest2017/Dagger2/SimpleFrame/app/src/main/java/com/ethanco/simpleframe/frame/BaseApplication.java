package com.ethanco.simpleframe.frame;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;
import com.tencent.bugly.crashreport.CrashReport;

/**
 * Created by Zhk on 2015/12/22.
 */
public abstract class BaseApplication extends Application {
    public static boolean IS_DEBUG = false;

    @Override
    public void onCreate() {
        super.onCreate();
        IS_DEBUG = isDebug();
        CrashReport.initCrashReport(getApplicationContext(), getBuglyAppID(), false);
        LeakCanary.install(this);
    }

    /**
     * Bugly申请的App的AppID
     *
     * @return
     */
    protected abstract String getBuglyAppID();

    /**
     * 是否是Debug状态
     *
     * @return
     */
    protected abstract boolean isDebug();
}
