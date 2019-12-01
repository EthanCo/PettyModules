package com.heiko.ssdpservertest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;
import java.net.DatagramPacket;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "Z-SSDP-Server";
    private Button btnStartSsdpServer;
    private TextView tvInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnStartSsdpServer = findViewById(R.id.btn_start_ssdp_server);
        tvInfo = findViewById(R.id.tv_info);
        btnStartSsdpServer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread() {
                    @Override
                    public void run() {
                        while (true){
                            Log.i(TAG, "启动SSDP服务器");
                            try {
                                SSDPSocket ssdpSocket = new SSDPSocket();
                                DatagramPacket dp = ssdpSocket.receive(); // Here, I only receive the same packets I initially sent above
                                final String c = new String(dp.getData()).trim();
                                final String ip = new String(dp.getAddress().toString()).trim();

                                /*if (c.startsWith("M-SEARCH * HTTP/1.1")) {
                                    String[] dataArray = c.split(SSDPConstants.NEWLINE);
                                    for (String s : dataArray) {
                                        Log.i(TAG, "" + s);
                                    }
                                }*/
                                SSDPBean ssdpBean = new SSDPBean(c);
                                Log.i(TAG, ssdpBean.toString());
                                
                                Log.i(TAG, "接收到的数据为：\n" + c + "數據來源IP：" + ip);
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        tvInfo.append("接收到的数据为：\n" + c + "數據來源IP：" + ip + "\r\n");
                                    }
                                });
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }.start();
            }
        });
    }
}
