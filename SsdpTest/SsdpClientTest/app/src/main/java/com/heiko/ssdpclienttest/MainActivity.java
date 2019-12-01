package com.heiko.ssdpclienttest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;
import java.net.DatagramPacket;

public class MainActivity extends AppCompatActivity {
    private Button btnSendSSDP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSendSSDP = (Button)findViewById(R.id.btn_send_ssdp);
        btnSendSSDP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "send SSDP", Toast.LENGTH_SHORT).show();
                new Thread(){
                    @Override
                    public void run() {
                        super.run();
                        SendMSearchMessage();
                    }
                }.start();
            }
        });
    }

    private void SendMSearchMessage() {
        // SSDPSearchMsg searchContentDirectory = new SSDPSearchMsg(SSDPConstants.ST_ContentDirectory);
        // SSDPSearchMsg searchAVTransport = new SSDPSearchMsg(SSDPConstants.ST_AVTransport);
        SSDPSearchMsg searchProduct = new SSDPSearchMsg(SSDPConstants.Root);
        SSDPSocket sock = null;
        try {
            sock = new SSDPSocket();
            for (int i = 0; i < 1; i++) {
        // sock.send(searchContentDirectory.toString());
        // sock.send(searchAVTransport.toString());
                sock.send(searchProduct.toString());
        // String s = "M-SEARCH * HTTP/1.1 \n HOST= 239.255.255.250:1900 \n MAN= \"ssdp:discover\" \n MX: 3 \n ST= upnp:rootdevice";
        // sock.send(s);
                Log.i("-------------", "发送的数据为：\n" + searchProduct.toString());
            }
            while (true) {
                DatagramPacket dp = sock.receive(); // Here, I only receive the same packets I initially sent above
                String c = new String(dp.getData()).trim();
                String ip = new String(dp.getAddress().toString()).trim();
                Log.i("------------", "接收到的数据为：\n" + c + "數據來源IP：" + ip);
            }
        } catch (IOException e) {
            Log.e("M-SEARCH", e.getMessage());
        }
    }
}
