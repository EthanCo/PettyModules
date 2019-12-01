package com.heiko.ssdpservertest;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.SocketAddress;

/**
 * SSDPSocket
 *
 * @author EthanCo
 * @since 2018/3/12
 */
public class SSDPSocket {

    SocketAddress mSSDPMulticastGroup;
    MulticastSocket mSSDPSocket;
    InetAddress broadcastAddress;

    public SSDPSocket() throws IOException {
        mSSDPSocket = new MulticastSocket(1900); // Bind some random port for receiving datagram
        broadcastAddress = InetAddress.getByName(SSDPConstants.ADDRESS);
        mSSDPSocket.joinGroup(broadcastAddress);
    }

    /* Used to send SSDP packet */
    public void send(String data) throws IOException {
        DatagramPacket dp = new DatagramPacket(data.getBytes(), data.length(), broadcastAddress, SSDPConstants.PORT);
        mSSDPSocket.send(dp);
    }

    /* Used to receive SSDP packet */
    public DatagramPacket receive() throws IOException {
        byte[] buf = new byte[1024];
        DatagramPacket dp = new DatagramPacket(buf, buf.length);
        mSSDPSocket.receive(dp);
        return dp;
    }

    public void close() {
        if (mSSDPSocket != null) {
            mSSDPSocket.close();
        }
    }
}
