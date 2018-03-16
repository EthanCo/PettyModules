package com.heiko.rokidssdptest;

import android.text.TextUtils;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 * UDP 工具类
 *
 * @author EthanCo
 * @since 2018/3/13
 */

public class UdpUtil {
    public static void send(final String ip, final int port, final String message) {
        if (TextUtils.isEmpty(message)) {
            return;
        }
        new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    DatagramSocket socket = new DatagramSocket(port);
                    InetAddress targetAddress = InetAddress.getByName(ip);
                    byte[] data = message.getBytes();
                    DatagramPacket datagramPacket = new DatagramPacket(data, data.length, targetAddress, port);
                    socket.send(datagramPacket);
                } catch (SocketException e) {
                    e.printStackTrace();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
}
