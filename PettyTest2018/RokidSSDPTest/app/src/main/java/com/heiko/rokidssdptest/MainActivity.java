package com.heiko.rokidssdptest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "Z-MainActivity";
    private static final String TAG_ROKID = "Z-MainActivity-Rokid";
    private Button btnStartSsdpServer;
    private SSDPSocket ssdpSocket;
    private TextView tvInfo;
    private Button btnUdpResponse;
    private SsdpHandler ssdpHandler = SsdpHandler.getInstance();
    private Button btnStopSsdpServer;

    public MainActivity() {
        try {
            ssdpSocket = new SSDPSocket(Consts.IP, Consts.PORT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnStartSsdpServer = findViewById(R.id.btn_start_ssdp_server);
        btnStopSsdpServer = findViewById(R.id.btn_stop_ssdp_server);
        btnUdpResponse = findViewById(R.id.btn_udp_response);
        tvInfo = findViewById(R.id.tv_info);
        btnStartSsdpServer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "start", Toast.LENGTH_SHORT).show();
                ssdpHandler.start();
            }
        });
        btnStopSsdpServer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "stop", Toast.LENGTH_SHORT).show();
                ssdpHandler.stop();
            }
        });

        btnUdpResponse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendUdpResponse("192.168.39.103", 36886);
            }
        });
    }

    private void sendUdpResponse(String ip, int port) {
        Log.i(TAG_ROKID, "UDP 端口:" + "ip:" + ip + " port:" + port);
        Map<String, Object> searchMap = SsdpFactory.createSearchResponse();
        String sendData = SsdpAssembly.generateDatagram(searchMap);
        //String sendData = SsdpAssembly.generateDatagramIncludeSpace(searchMap);
        Log.i(TAG_ROKID, "udp 发送:\n" + sendData);
        UdpUtil.send(ip, port, sendData);
    }
}
