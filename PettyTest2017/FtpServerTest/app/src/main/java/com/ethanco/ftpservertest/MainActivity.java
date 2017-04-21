package com.ethanco.ftpservertest;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.lib.ftpserver.FtpUtil;

public class MainActivity extends Activity {
    private FtpUtil ftpUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ftpUtil = App.getInstance().getFtpUtil();
    }

    public void onStartServer(View view) {
        ftpUtil.startServer();
    }

    public void onStopServer(View view) {
        ftpUtil.stopServer();
    }
}