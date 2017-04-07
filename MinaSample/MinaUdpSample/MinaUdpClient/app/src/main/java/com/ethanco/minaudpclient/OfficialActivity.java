package com.ethanco.minaudpclient;

import android.content.Context;
import android.net.wifi.WifiManager;
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
import org.apache.mina.transport.socket.nio.NioDatagramConnector;

import java.net.InetSocketAddress;

//官方Demo
//http://mina.apache.org/mina-project/userguide/ch2-basics/sample-udp-client.html
public class OfficialActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int PORT = 3344;
    public static final String TAG = "Z-MinaUdpClient";
    private Button btnConn;
    private Button btnSendData;
    private NioDatagramConnector connector;
    private IoSession session;
    private WifiManager.MulticastLock lock;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        System.setProperty("java.net.preferIPv6Addresses", "false");

        btnConn = (Button) findViewById(R.id.btn_conn);
        btnSendData = (Button) findViewById(R.id.btn_send_data);
        btnConn.setOnClickListener(this);
        btnSendData.setOnClickListener(this);

        WifiManager manager = (WifiManager) getApplicationContext()
                .getSystemService(Context.WIFI_SERVICE);
        lock = manager.createMulticastLock("test wifi");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_conn:
                new Thread() {
                    @Override
                    public void run() {
                        lock.acquire();
                        connector = new NioDatagramConnector();
                        connector.setHandler(new Defaulthandler());
                        ConnectFuture connFuture = connector.connect(new InetSocketAddress("192.168.39.100", PORT));
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
                                    Log.i(TAG, "Not connected...exiting");
                                }
                            }
                        });
                        lock.release();
                    }
                }.start();
                break;
            case R.id.btn_send_data:
                new Thread() {
                    @Override
                    public void run() {
                        try {
                            sendData();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }.start();
                break;
            default:
        }
    }

    private void sendData() throws InterruptedException {
        for (int i = 0; i < 1; i++) {
            IoBuffer buffer = IoBuffer.allocate(8);
            buffer.putLong(15L);
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

        @Override
        public void sessionCreated(IoSession session) throws Exception {
            super.sessionCreated(session);
            Log.i(TAG, "sessionCreated");
        }

        @Override
        public void sessionOpened(IoSession session) throws Exception {
            super.sessionOpened(session);
            Log.i(TAG, "sessionOpened");
        }

        @Override
        public void messageReceived(IoSession session, Object message) throws Exception {
            super.messageReceived(session, message);
            Log.i(TAG, "messageReceived:" + message);
        }

        @Override
        public void messageSent(IoSession session, Object message) throws Exception {
            super.messageSent(session, message);
            Log.i(TAG, "messageSent");
        }

        @Override
        public void sessionClosed(IoSession session) throws Exception {
            super.sessionClosed(session);
            Log.i(TAG, "sessionClosed");
        }
    }
}
