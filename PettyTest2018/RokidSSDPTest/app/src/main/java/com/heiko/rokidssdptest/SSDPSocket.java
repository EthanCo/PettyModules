package com.heiko.rokidssdptest;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.concurrent.ExecutorService;

/**
 * SSDPSocket
 *
 * @author EthanCo
 * @since 2018/3/12
 */
public class SSDPSocket {
    private static final int BUFFER_SIZE = 1024;

    //SocketAddress mSSDPMulticastGroup;
    MulticastSocket mSSDPSocket;
    InetAddress broadcastAddress;
    private int port;
    private ExecutorService threadPool;

    public SSDPSocket(String ip, int port) throws IOException {
        threadPool = ThreadPool.getInstance().getExecutorPool();
        this.port = port;
        mSSDPSocket = new MulticastSocket(port); // Bind some random port for receiving datagram
        broadcastAddress = InetAddress.getByName(ip);
        mSSDPSocket.joinGroup(broadcastAddress);
    }

    /* Used to send SSDP packet */
    public void send(final String data) {
        threadPool.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    DatagramPacket dp = new DatagramPacket(data.getBytes(), data.length(), broadcastAddress, port);
                    mSSDPSocket.send(dp);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /* Used to receive SSDP packet */
    public DatagramPacket receive() throws IOException {
        byte[] buf = new byte[BUFFER_SIZE];
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
