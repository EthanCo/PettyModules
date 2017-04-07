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
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioDatagramConnector;

import java.net.InetSocketAddress;

//http://mina.apache.org/mina-project/userguide/ch2-basics/sample-udp-client.html
public class OtherActivity03 extends AppCompatActivity implements View.OnClickListener {

    private static final int PORT = 3344;
    public static final String TAG = "Z-MinaUdpClient";
    private Button btnConn;
    private Button btnSendData;
    private IoSession session;
    private ConnectFuture connFuture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        System.setProperty("java.net.preferIPv6Addresses", "false");

        btnConn = (Button) findViewById(R.id.btn_conn);
        btnSendData = (Button) findViewById(R.id.btn_send_data);

        btnConn.setOnClickListener(this);
        btnSendData.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_conn:
                NioDatagramConnector connector = new NioDatagramConnector();
                connector.setHandler(new Defaulthandler());
                connector.getFilterChain().addLast("logger", new LoggingFilter());

                connFuture = connector.connect(new InetSocketAddress("192.168.39.100", PORT));
                //connFuture.awaitUninterruptibly();
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
            case R.id.btn_send_data:
                break;
            default:
        }
    }

    private void sendData() throws InterruptedException {
        new Thread() {
            @Override
            public void run() {
                long free = Runtime.getRuntime().freeMemory();
                IoBuffer buffer = IoBuffer.allocate(8);
                buffer.putLong(free);
                buffer.flip();
                session.write(buffer);
                session.closeOnFlush();
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
