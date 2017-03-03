package com.ethanco.udptestserver;

import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class MainActivity extends AppCompatActivity {

    private static WifiManager.MulticastLock lock;
    private TextView tvInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvInfo = (TextView) findViewById(R.id.tv_info);

        WifiManager manager = (WifiManager) getSystemService(WIFI_SERVICE);
        this.lock = manager.createMulticastLock("UDPwifi");

        new Thread() {
            @Override
            public void run() {
                StartListen();
            }
        }.start();
    }

    public void StartListen() {
        // UDP服务器监听的端口
        Integer port = 18903;
        // 接收的字节大小，客户端发送的数据不能超过这个大小
        byte[] message = new byte[100];
        try {
            // 建立Socket连接
            DatagramSocket datagramSocket = new DatagramSocket(port);
            datagramSocket.setBroadcast(true);
            final DatagramPacket datagramPacket = new DatagramPacket(message,
                    message.length);
            try {
                while (true) {
                    // 准备接收数据
                    Log.d("UDP Demo", "准备接受");
                    this.lock.acquire();

                    datagramSocket.receive(datagramPacket);
                    final String strMsg = new String(datagramPacket.getData()).trim();
                    Log.d("UDP Demo", datagramPacket.getAddress()
                            .getHostAddress().toString() + ":" + strMsg);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            tvInfo.append(datagramPacket.getAddress().getHostAddress().toString() + ":" + strMsg + "\r\n");
                        }
                    });
                    this.lock.release();
                }
            } catch (IOException e) {//IOException
                e.printStackTrace();
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }

    }
}
