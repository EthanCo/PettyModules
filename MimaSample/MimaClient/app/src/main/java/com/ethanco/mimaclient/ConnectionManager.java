package com.ethanco.mimaclient;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;

import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.serialization.ObjectSerializationCodecFactory;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

import java.lang.ref.WeakReference;
import java.net.InetSocketAddress;

/**
 * 封装好Connection dissconection方法供外层调用
 * Created by EthanCo on 2016/12/5.
 */

public class ConnectionManager {
    private static final String BROADCAST_ACTION = "com.ethanco.minaclient.mina";
    private static final String MESSAGE = "message";
    private ConnectionConfig mConfig;
    private WeakReference<Context> mContextRef;
    private NioSocketConnector mConnection;
    private InetSocketAddress mAddress;
    private IoSession mSession;

    public ConnectionManager(ConnectionConfig config) {
        this.mConfig = config;
        this.mContextRef = new WeakReference<Context>(config.getContext());
        init();
    }

    private void init() {
        Context context = mContextRef.get();
        if (context == null) return;

        mAddress = new InetSocketAddress(mConfig.getIp(), mConfig.getPort());
        mConnection = new NioSocketConnector();
        mConnection.getSessionConfig().setReadBufferSize(mConfig.getReadBufferSize());
        mConnection.getFilterChain().addLast("logging", new LoggingFilter());
        mConnection.getFilterChain().addLast("codec", new ProtocolCodecFilter(
                new ObjectSerializationCodecFactory()));
        mConnection.setHandler(new Defaulthandler(mContextRef.get()));
    }

    /**
     * 进行连接
     *
     * @return
     */
    public boolean connect() {
        try {
            ConnectFuture future = mConnection.connect();
            future.awaitUninterruptibly();//一直等到它连接为止
            mSession = future.getSession();
        } catch (Exception e) {
            return false;
        }

        return mSession == null ? false : true;
    }

    /**
     * 断开连接
     */
    public void disConnection() {
        mConnection.dispose();
        mConnection = null;
        mSession = null;
        mAddress = null;
        mContextRef = null;
    }

    private static class Defaulthandler extends IoHandlerAdapter {
        private Context mContext;

        Defaulthandler(Context context) {
            this.mContext = context;
        }

        //会话(session)创建之后，回调该方法
        @Override
        public void sessionCreated(IoSession session) throws Exception {
            super.sessionCreated(session);
        }

        //会话(session)打开之后，回调该方法
        @Override
        public void sessionOpened(IoSession session) throws Exception {
            super.sessionOpened(session);
            //将我们的session保存到我们的session manager类中，从而可以发送消息到服务器
        }

        //接收到消息时回调这个方法
        @Override
        public void messageReceived(IoSession session, Object message) throws Exception {
            super.messageReceived(session, message);
            if (mContext != null) {
                Intent intent = new Intent(BROADCAST_ACTION);
                intent.putExtra(MESSAGE, message.toString());
                //局部广播，其他App接收不到。既高效又安全
                LocalBroadcastManager.getInstance(mContext).sendBroadcast(intent);
            }
        }

        //发送数据是回调这个方法
        @Override
        public void messageSent(IoSession session, Object message) throws Exception {
            super.messageSent(session, message);
        }

        //会话(session)关闭后，回调该方法
        @Override
        public void sessionClosed(IoSession session) throws Exception {
            super.sessionClosed(session);
        }
    }
}
