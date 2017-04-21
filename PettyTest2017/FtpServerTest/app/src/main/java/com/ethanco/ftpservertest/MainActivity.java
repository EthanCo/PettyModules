package com.ethanco.ftpservertest;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.lib.ftpserver.FtpDirector;

public class MainActivity extends Activity {
    private FtpDirector ftpDirector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ftpDirector = App.getInstance().getFtpDirector();
    }

    public void onStartServer(View view) {
        ftpDirector.startServer();
    }

    public void onStopServer(View view) {
        ftpDirector.stopServer();
    }
}