package com.ethanco.minaudpclient;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.future.IoFuture;
import org.apache.mina.core.future.IoFutureListener;
import org.apache.mina.core.service.IoConnector;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioDatagramConnector;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;

//http://mina.apache.org/mina-project/userguide/ch2-basics/sample-udp-client.html
public class OtherActivity02 extends AppCompatActivity implements View.OnClickListener {

    private static final int PORT = 9123;
    public static final String TAG = "Z-MinaUdpClient";
    private Button btnConn;
    private Button btnSendData;
    private IoSession session;
    private ConnectFuture connFuture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnConn = (Button) findViewById(R.id.btn_conn);
        btnSendData = (Button) findViewById(R.id.btn_send_data);

        btnConn.setOnClickListener(this);
        btnSendData.setOnClickListener(this);
    }

    public void send(String host, int port) {

        try {
            InetAddress ia = InetAddress.getByName(host);
            DatagramSocket socket = new DatagramSocket(9999);
            socket.connect(ia, port);
            byte[] buffer = new byte[1024];

            buffer = ("22")
                    .getBytes();
            DatagramPacket dp = new DatagramPacket(buffer, buffer.length);
            System.out.println(dp.getLength());
            DatagramPacket dp1 = new DatagramPacket(new byte[22312], 22312);
            socket.send(dp);
            socket.receive(dp1);
            byte[] bb = dp1.getData();
            for (int i = 0; i < dp1.getLength(); i++) {
                System.out.print((char) bb[i]);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void send1(InetAddress addr, int port, final byte[] data) {
        IoConnector connector = new NioDatagramConnector();
        connector.setHandler(new Defaulthandler());
        ConnectFuture future = connector.connect(new InetSocketAddress(addr.getHostAddress(), port));
        connector.getFilterChain().addLast("logger", new LoggingFilter());

        future.awaitUninterruptibly();
        future.addListener(new IoFutureListener<ConnectFuture>() {

            public void operationComplete(ConnectFuture future) {
                if (future.isConnected()) {
                    IoSession ioSession = future.getSession();
                    /*
                     * this is just for heap buffer.
                	 * if true derict buffer will be returned , and then it cannot be gc.
                	 * easy to OOM exception
                	 */
                    IoBuffer buffer = IoBuffer.allocate(data.length, false);
                    buffer.put(data);
                    /*
                     * Flips this buffer.
                     * The limit is set to the current position and then the position is set to zero.
                     * If the mark is defined then it is discarded
                     * This method is often used in conjunction with the compact method when transferring data from one place to another.
                     * @see Buffer#compact
                     */
                    buffer.flip();
                    //buffer.compact();
                    //buffer.put(someobj);
                    //buffer.flip();
                    ioSession.write(buffer);
                } else {
                    System.out.println("Not connected...exiting");
                }
            }
        });
        future.cancel();
        //future.getSession().getCloseFuture().awaitUninterruptibly();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_conn:
//              new Thread(){
//                  @Override
//                  public void run() {
//                      try {
//                          InetAddress address = InetAddress.getLocalHost();
//                          send1(address, PORT, "hello".getBytes());
//                      } catch (UnknownHostException e) {
//                          e.printStackTrace();
//                      }
//                  }
//              }.start();

//                new Thread(){
//                    @Override
//                    public void run() {
//                        send("255.255.255.255", PORT);
//                    }
//                }.start();

                new Thread() {
                    @Override
                    public void run() {
                        try {
                            InetAddress address = InetAddress.getLocalHost();
                            NioDatagramConnector connector = new NioDatagramConnector();
                            connector.setHandler(new Defaulthandler());
                            connector.getFilterChain().addLast("logger", new LoggingFilter());

                            //                DatagramSessionConfig dcfg = connector.getSessionConfig();//建立连接的配置文件
                            //                dcfg.setReadBufferSize(4096);//设置接收最大字节默认2048
                            //                dcfg.setReceiveBufferSize(1024);//设置输入缓冲区的大小
                            //                dcfg.setSendBufferSize(1024);//设置输出缓冲区的大小
                            //                dcfg.setReuseAddress(true);//设置每一个非主监听连接的端口可以重用
                            //                connFuture = connector.connect( new InetSocketAddress(PORT));

                            //connFuture = connector.connect(new InetSocketAddress("255.255.255.255", PORT));
                            connFuture = connector.connect(new InetSocketAddress(address.getHostAddress(), PORT));
                        } catch (UnknownHostException e) {
                            e.printStackTrace();
                        }
                    }
                }.start();
                break;
            case R.id.btn_send_data:
                connFuture.addListener(new IoFutureListener() {
                    public void operationComplete(IoFuture future) {
                        ConnectFuture connFuture = (ConnectFuture) future;
                        if (connFuture.isConnected()) {
                            session = future.getSession();
                            try {
                                sendData();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        } else {
                            Log.e(TAG, "Not connected...exiting");
                        }
                    }
                });
                break;
            default:
        }



        /*NioDatagramAcceptor acceptor = new NioDatagramAcceptor();//创建一个UDP的接收器
        acceptor.setHandler(new Defaulthandler());//设置接收器的处理程序

        //Executor threadPool = Executors.newFixedThreadPool(1500);//建立线程池
        //acceptor.getFilterChain().addLast("exector", new ExecutorFilter(threadPool));
        acceptor.getFilterChain().addLast("logger", new LoggingFilter());

        DatagramSessionConfig dcfg = acceptor.getSessionConfig();//建立连接的配置文件
        dcfg.setReadBufferSize(4096);//设置接收最大字节默认2048
        dcfg.setReceiveBufferSize(1024);//设置输入缓冲区的大小
        dcfg.setSendBufferSize(1024);//设置输出缓冲区的大小
        dcfg.setReuseAddress(true);//设置每一个非主监听连接的端口可以重用

        try {
            acceptor.bind(new InetSocketAddress(PORT));//绑定端口

        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }

    private void sendData() throws InterruptedException {
        for (int i = 0; i < 2; i++) {
            long free = Runtime.getRuntime().freeMemory();
            IoBuffer buffer = IoBuffer.allocate(8);
            buffer.putLong(free);
            buffer.flip();
            session.write(buffer);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
                throw new InterruptedException(e.getMessage());
            }
        }
    }

    private static class Defaulthandler extends IoHandlerAdapter {

        //会话(session)创建之后，回调该方法
        @Override
        public void sessionCreated(IoSession session) throws Exception {
            super.sessionCreated(session);
            Log.i(TAG, "sessionCreated");
        }

        //会话(session)打开之后，回调该方法
        @Override
        public void sessionOpened(IoSession session) throws Exception {
            super.sessionOpened(session);
            //将我们的session保存到我们的session manager类中，从而可以发送消息到服务器
            Log.i(TAG, "sessionOpened");
        }

        //接收到消息时回调这个方法
        @Override
        public void messageReceived(IoSession session, Object message) throws Exception {
            super.messageReceived(session, message);
            Log.i(TAG, "messageReceived:" + message);
        }

        //发送数据是回调这个方法
        @Override
        public void messageSent(IoSession session, Object message) throws Exception {
            super.messageSent(session, message);
            Log.i(TAG, "messageSent");
        }

        //会话(session)关闭后，回调该方法
        @Override
        public void sessionClosed(IoSession session) throws Exception {
            super.sessionClosed(session);
            Log.i(TAG, "sessionClosed");
        }
    }
}
