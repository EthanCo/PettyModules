package com.ethanco.myapplication;

import android.app.Application;

import com.ethanco.lib.bugly.BuglyWrap;
import com.tencent.bugly.beta.Beta;

/**
 * Created by EthanCo on 2016/11/9.
 */

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        //自动检查更新开关 true表示初始化时自动检查升级; false表示不会自动检查升级,需要手动调用Beta.checkUpgrade()方法;
        Beta.autoCheckUpgrade = false;//关闭自动检查更新开关，手动在mainActivity的OnResume中调用Beta.checkUpgrade()
        BuglyWrap.initAndCustomDialog(getApplicationContext(), "7f525d2734", false, R.mipmap.ic_launcher);
    }
}
