package com.ethanco.ftpservertest;

import android.app.Application;

import com.lib.ftpserver.FtpUtil;
import com.lib.ftpserver.Utility;

/**
 * Application
 *
 * @author EthanCo
 * @since 2017/4/21
 */
public class App extends Application {
    private static App instance;
    private FtpUtil ftpUtil;

    public FtpUtil getFtpUtil() {
        return ftpUtil;
    }

    public static App getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;
        //ftpUtil = new FtpUtil(this, 2224, Utility.getCustomDir(this, "mmm"));
        //ftpUtil = new FtpUtil(this, 15673, Utility.getCustomDir(this, "gsb"));
        //ftpUtil = new FtpUtil(this, 15672);
        //ftpUtil = new FtpUtil(this, 15674, Utility.getCustomDir(this, "ccm"),Utility.getCustomDir(this, "bbconfig"));
        ftpUtil = new FtpUtil(this, 15675, Utility.getCustomDir(this, "HopeLauncher", ".nomedia"), Utility.getCustomDir(this, "FtpConfig"));
    }
}
