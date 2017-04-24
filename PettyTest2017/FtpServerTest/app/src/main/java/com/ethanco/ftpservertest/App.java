package com.ethanco.ftpservertest;

import android.app.Application;

import com.lib.ftpserver.FtpDirector;
import com.lib.ftpserver.FtpUtil;

/**
 * Application
 *
 * @author EthanCo
 * @since 2017/4/21
 */
public class App extends Application {
    private static App instance;
    private FtpDirector ftpDirector;

    public FtpDirector getFtpDirector() {
        return ftpDirector;
    }

    public static App getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;
        //ftpUtil = new FtpUtil(this, 2224, Utility.getCustomDir(this, "ftp"));
        String ftpDir = FtpUtil.getCustomDir(this, "HopeLauncher", ".nomedia");
        String configDir = FtpUtil.getCustomDir(this, "FtpConfig");
        ftpDirector = new FtpDirector(this, 15676, ftpDir, configDir);
    }
}
