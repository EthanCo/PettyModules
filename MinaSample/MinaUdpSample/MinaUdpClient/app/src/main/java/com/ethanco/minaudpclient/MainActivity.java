package com.ethanco.minaudpclient;

import android.content.Context;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import org.apache.mina.core.filterchain.DefaultIoFilterChainBuilder;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.future.IoFuture;
import org.apache.mina.core.future.IoFutureListener;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.LineDelimiter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioDatagramConnector;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;

//http://mina.apache.org/mina-project/userguide/ch2-basics/sample-udp-client.html
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String TAG = "Z-MinaUdpClient";
    private static final int PORT = 3344;
    private Button btnConn;
    private NioDatagramConnector connector;
    private IoSession session;
    private WifiManager.MulticastLock lock;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        System.setProperty("java.net.preferIPv6Addresses", "false");

        btnConn = (Button) findViewById(R.id.btn_conn);
        btnConn.setOnClickListener(this);

        WifiManager manager = (WifiManager) this
                .getSystemService(Context.WIFI_SERVICE);
        lock= manager.createMulticastLock("test wifi");
    }

    @Override
    public void onClick(View view) {
        new Thread() {
            @Override
            public void run() {
                lock.acquire();

                connector = new NioDatagramConnector();
                connector.setHandler(new Defaulthandler());
                DefaultIoFilterChainBuilder chain = connector.getFilterChain();
                chain.addLast("logger", new LoggingFilter());
                chain.addLast("codec", new ProtocolCodecFilter(
                        new TextLineCodecFactory(Charset.forName("UTF-8"),
                                LineDelimiter.NUL, LineDelimiter.NUL)));
//                IoFuture connFuture = connector.connect(new InetSocketAddress(
//                        "192.168.2.18", PORT));
//                IoFuture connFuture = connector.connect(new InetSocketAddress(
//                        "192.168.2.155", PORT));
//                IoFuture connFuture = connector.connect(new InetSocketAddress(
//                        "255.255.255.255", PORT));
//                IoFuture connFuture = connector.connect(new InetSocketAddress(
//                        "192.168.255.255", PORT));
//                IoFuture connFuture = connector.connect(new InetSocketAddress(
//                        "192.168.2.255", PORT));
//                IoFuture connFuture = connector.connect(new InetSocketAddress(
//                        "192.168.2.255", PORT));
//                IoFuture connFuture = connector.connect(new InetSocketAddress(
//                        "224.0.0.1", PORT));
                IoFuture connFuture = connector.connect(new InetSocketAddress(
                        "192.168.1.103", PORT));
                connFuture.awaitUninterruptibly();
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

                lock.release();
            }
        }.start();
    }

    private void sendData() throws InterruptedException {
        session.write("测试数据发送！");
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
