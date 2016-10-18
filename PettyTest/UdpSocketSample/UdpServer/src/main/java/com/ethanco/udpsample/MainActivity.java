package com.ethanco.udpsample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Thread() {
            @Override
            public void run() {
                String serverHost = "127.0.0.1";
                int serverPort = 3344;
                UdpServerSocket udpServerSocket = null;
                try {
                    udpServerSocket = new UdpServerSocket(serverHost, serverPort);
                    while (true) {
                        udpServerSocket.receive();
                        udpServerSocket.response("Z-你好,sterning!");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    class UdpServerSocket {
        private byte[] buffer = new byte[1024];

        private DatagramSocket ds = null;

        private DatagramPacket packet = null;

        private InetSocketAddress socketAddress = null;

        private String orgIp;

        /**
         * 构造函数，绑定主机和端口.
         *
         * @param host 主机
         * @param port 端口
         * @throws Exception
         */
        public UdpServerSocket(String host, int port) throws Exception {
            socketAddress = new InetSocketAddress(host, port);
            ds = new DatagramSocket(socketAddress);
            System.out.println("Z-服务端启动!");
        }

        public final String getOrgIp() {
            return orgIp;
        }

        /**
         * 设置超时时间，该方法必须在bind方法之后使用.
         *
         * @param timeout 超时时间
         * @throws Exception
         */
        public final void setSoTimeout(int timeout) throws Exception {
            ds.setSoTimeout(timeout);
        }

        /**
         * 获得超时时间.
         *
         * @return 返回超时时间.
         * @throws Exception
         */
        public final int getSoTimeout() throws Exception {
            return ds.getSoTimeout();
        }

        /**
         * 绑定监听地址和端口.
         *
         * @param host 主机IP
         * @param port 端口
         * @throws SocketException
         */
        public final void bind(String host, int port) throws SocketException {
            socketAddress = new InetSocketAddress(host, port);
            ds = new DatagramSocket(socketAddress);
        }


        /**
         * 接收数据包，该方法会造成线程阻塞.
         *
         * @return 返回接收的数据串信息
         * @throws IOException
         */
        public final String receive() throws IOException {
            packet = new DatagramPacket(buffer, buffer.length);
            ds.receive(packet);
            orgIp = packet.getAddress().getHostAddress();
            String info = new String(packet.getData(), 0, packet.getLength());
            System.out.println("Z-接收信息：" + info);
            return info;
        }

        /**
         * 将响应包发送给请求端.
         *
         * @param info 回应报文
         * @throws IOException
         */
        public final void response(String info) throws IOException {
            System.out.println("Z-客户端地址 : " + packet.getAddress().getHostAddress()
                    + ",端口：" + packet.getPort());
            DatagramPacket dp = new DatagramPacket(buffer, buffer.length, packet
                    .getAddress(), packet.getPort());
            dp.setData(info.getBytes());
            ds.send(dp);
        }

        /**
         * 设置报文的缓冲长度.
         *
         * @param bufsize 缓冲长度
         */
        public final void setLength(int bufsize) {
            packet.setLength(bufsize);
        }

        /**
         * 获得发送回应的IP地址.
         *
         * @return 返回回应的IP地址
         */
        public final InetAddress getResponseAddress() {
            return packet.getAddress();
        }

        /**
         * 获得回应的主机的端口.
         *
         * @return 返回回应的主机的端口.
         */
        public final int getResponsePort() {
            return packet.getPort();
        }

        /**
         * 关闭udp监听口.
         */
        public final void close() {
            try {
                ds.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
