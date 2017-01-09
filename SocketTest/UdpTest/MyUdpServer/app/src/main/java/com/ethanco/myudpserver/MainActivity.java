package com.ethanco.myudpserver;

import android.content.Context;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private WifiManager.MulticastLock lock;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_start).setOnClickListener(this);

        WifiManager manager = (WifiManager) this
                .getSystemService(Context.WIFI_SERVICE);
        lock= manager.createMulticastLock("test wifi");
    }

    @Override
    public void onClick(View view) {
        new Thread() {
            @Override
            public void run() {
                lock.acquire();
                String serverHost = "192.168.2.18";
                int serverPort = 3344;
                MyServer udpServerSocket = null;
                try {
                    udpServerSocket = new MyServer(serverHost, serverPort);
                    while (true) {
                        udpServerSocket.receive();
                        udpServerSocket.response("你好,吃了吗!");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                lock.release();
            }
        }.start();
    }
}
