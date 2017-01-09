package com.ethanco.myudpserver;

import android.content.Context;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "Z-UdpServer";
    public static final int PORT = 3344;
    private WifiManager.MulticastLock lock;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_start).setOnClickListener(this);

        WifiManager manager = (WifiManager) this
                .getSystemService(Context.WIFI_SERVICE);
        lock = manager.createMulticastLock("test wifi");
    }

    @Override
    public void onClick(View view) {
        new Thread() {
            @Override
            public void run() {
                lock.acquire();

                String lanIP = Utils.getLANIP(getApplication());
                String serverHost = lanIP;
                int serverPort = PORT;
                Log.i(TAG, "服务器启动 server ip" + serverHost + ":" + serverPort);
                MyServer udpServerSocket = null;
                try {
                    udpServerSocket = new MyServer(serverHost, serverPort);
                    while (true) {
                        String receive = udpServerSocket.receive();
                        Log.i(TAG, "收到数据:" + receive);
                        String sendData = "你好,吃了吗!";
                        udpServerSocket.response(sendData);
                        Log.i(TAG, "发送数据:" + sendData);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                lock.release();
            }
        }.start();
    }
}
