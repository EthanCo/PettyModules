package com.ethanco.myudpclient;

import android.content.Context;
import android.net.wifi.WifiManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static final int PORT = 3344;
    public static final String TAG = "Z-UdpClient";
    private Button btnConnect;
    private WifiManager.MulticastLock lock;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnConnect = (Button) findViewById(R.id.btn_connect);
        btnConnect.setOnClickListener(this);

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
                MyClient client = null;
                try {
                    if (client == null) {
                        client = new MyClient();
                    }
                    //String serverHost = "127.0.0.1";
                    //String serverHost = "192.168.0.0";
                    //String serverHost = "255.255.255.255";
                    String serverHost = "192.168.1.103";
                    int serverPort = PORT;
                    String sendData = "你好，亲爱的!777";
                    Log.i(TAG, "发送数据 server ip:" + serverHost + ":" + serverPort + " " + sendData);
                    client.send(serverHost, serverPort, sendData.getBytes());
                    byte[] bt = client.receive();
                    Log.i(TAG, "服务端回应数据：" + new String(bt));
                    client.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                lock.release();
            }
        }.start();

    }
}
