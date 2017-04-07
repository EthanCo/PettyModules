package com.ethanco.minaudpserver;

import android.content.Context;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import org.apache.mina.core.filterchain.DefaultIoFilterChainBuilder;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.DatagramSessionConfig;
import org.apache.mina.transport.socket.nio.NioDatagramAcceptor;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;

//详情见 http://mina.apache.org/mina-project/userguide/ch2-basics/sample-udp-server.html
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int PORT = 3344;
    private Button btnStartServer;
    public static final String TAG = "Z-MinaUdpServer";
    private WifiManager.MulticastLock lock;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        System.setProperty("java.net.preferIPv6Addresses", "false");

        btnStartServer = (Button) findViewById(R.id.btn_start_server);
        btnStartServer.setOnClickListener(this);

        WifiManager manager = (WifiManager) getApplicationContext()
                .getSystemService(Context.WIFI_SERVICE);
        lock = manager.createMulticastLock("test wifi");
    }

    @Override
    public void onClick(View view) {
        new Thread() {
            @Override
            public void run() {
                lock.acquire();

                NioDatagramAcceptor acceptor = new NioDatagramAcceptor();
                acceptor.setHandler(new Defaulthandler());
                DefaultIoFilterChainBuilder chain = acceptor.getFilterChain();
                chain.addLast("logger", new LoggingFilter());
                chain.addLast("codec", new ProtocolCodecFilter(
                        new TextLineCodecFactory(Charset.forName("UTF-8"))));
                /*//没有任何换号标记
                chain.addLast("codec", new ProtocolCodecFilter(
                        new TextLineCodecFactory(Charset.forName("UTF-8"),
                                LineDelimiter.NUL, LineDelimiter.NUL)));*/
                DatagramSessionConfig dcfg = acceptor.getSessionConfig();
                dcfg.setReuseAddress(true);
                try {
                    String lanIP = Utils.getLANIP(getApplication());
                    Log.i(TAG, "服务器启动 serverIP:" + lanIP + " port:" + PORT);
                    acceptor.bind(new InetSocketAddress(PORT));
                } catch (IOException e) {
                    e.printStackTrace();
                }

                lock.release();
            }
        }.start();

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
            session.write("hello world");
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
