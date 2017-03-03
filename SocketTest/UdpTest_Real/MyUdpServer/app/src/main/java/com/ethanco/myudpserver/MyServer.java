package com.ethanco.myudpserver;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

/**
 * UDP服务类
 */
public class MyServer {

	private byte[] buffer = new byte[1024];
	private static DatagramSocket ds = null;
	private DatagramPacket packet = null;
	private InetSocketAddress socketAddress = null;

	/**
	 * 测试方法
	 */
	public static void main(String[] args) throws Exception {
		String serverHost = "127.0.0.1";
		int serverPort = 3344;
		MyServer udpServerSocket = new MyServer(serverHost,serverPort);
		while (true) {
			udpServerSocket.receive();
			udpServerSocket.response("你好,吃了吗!");
		}
	}

	/**
	 * 构造函数，绑定主机和端口
	 */
	public MyServer(String host, int port) throws Exception {
		socketAddress = new InetSocketAddress(host, port);
		ds = new DatagramSocket(socketAddress);
		System.out.println("服务端启动!");
	}

	/**
	 * 接收数据包，该方法会造成线程阻塞
	 */
	public final String receive() throws IOException {
		packet = new DatagramPacket(buffer, buffer.length);
		ds.receive(packet);
		String info = new String(packet.getData(), 0, packet.getLength());
		System.out.println("接收信息：" + info);
		return info;
	}

	/**
	 * 将响应包发送给请求端
	 */
	public final void response(String info) throws IOException {
		System.out.println("客户端地址 : " + packet.getAddress().getHostAddress()
				+ ",端口：" + packet.getPort());
		DatagramPacket dp = new DatagramPacket(buffer, buffer.length, packet
				.getAddress(), packet.getPort());
		dp.setData(info.getBytes());
		ds.send(dp);
	}
}