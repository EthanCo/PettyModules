package com.ethanco.myudpclient;

import android.content.Context;
import android.net.wifi.WifiManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

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
        lock= manager.createMulticastLock("test wifi");
    }

    @Override
    public void onClick(View view) {
       new Thread(){
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
                   String serverHost = "255.255.255.255";
                   int serverPort = 3344;
                   client.send(serverHost, serverPort, ("你好，亲爱的!666").getBytes());
                   byte[] bt = client.receive();
                   System.out.println("服务端回应数据：" + new String(bt));
                   client.close();
               } catch (Exception e) {
                   e.printStackTrace();
               }
               lock.release();
           }
       }.start();

    }
}
