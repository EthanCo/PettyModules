package com.ethanco.multicastsocketserver;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Z-Server";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Thread() {
            @Override
            public void run() {
                try {
                    receiveMultiBroadcast();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    protected void receiveMultiBroadcast() throws IOException {
        Log.v(TAG, "receiveMultiBroadcast...");
        MulticastSocket socket = new MulticastSocket(8601);
        InetAddress address = InetAddress.getByName("224.0.0.1");
        socket.joinGroup(address);

        DatagramPacket packet;

        // 接收数据
        Log.v(TAG, "receiver packet");
        byte[] rev = new byte[512];
        packet = new DatagramPacket(rev, rev.length);
        socket.receive(packet);
        String receiver = new String(packet.getData()).trim();  //不加trim，则会打印出512个byte，后面是乱码
        Log.v(TAG, "get data = " + receiver);

        //发送数据包
        Log.v(TAG, "send packet");
        String process_receiver = "I am MultiSocketB, I got " + receiver;
        byte[] buf = process_receiver.getBytes();
        packet = new DatagramPacket(buf, buf.length, address, 8600);
        socket.send(packet);

        //退出组播
        socket.leaveGroup(address);
        socket.close();
    }
}
