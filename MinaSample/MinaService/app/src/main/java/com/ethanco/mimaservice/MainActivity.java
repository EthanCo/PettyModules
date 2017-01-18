package com.ethanco.mimaservice;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.serialization.ObjectSerializationCodecFactory;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

import java.net.InetSocketAddress;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnStartMima;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnStartMima = (Button) findViewById(R.id.btn_start_mima_server);
        btnStartMima.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        //创建一个IoAcceptor
        NioSocketAcceptor acceptor = new NioSocketAcceptor();
        //得到Mima为我们提供的默认的日志过滤器
        acceptor.getFilterChain().addLast("Logger", new LoggingFilter());
        //添加Codec过滤器
        acceptor.getFilterChain().addLast("codec", new ProtocolCodecFilter(new ObjectSerializationCodecFactory()));
        //设置事件处理Handler
        acceptor.setHandler(new DemoServerhandler());
        //设置读缓存区大小
        acceptor.getSessionConfig().setReadBufferSize(2048);
        //设置空闲时间 10后没有任何读写，回到空闲状态
        //IdleStatus.BOTH_IDLE 读和写 IdleStatus.READER_IDLE 读 IdleStatus.WRITER_IDLE 写
        acceptor.getSessionConfig().setIdleTime(IdleStatus.WRITER_IDLE, 10);
        acceptor.setReuseAddress(true); //避免重启时提示地址被占用
        //开始监听客户端的连接 端口为9123
        try {
            acceptor.bind(new InetSocketAddress(9123));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //负责session对象的创建监听以及消息发送和接收的监听
    private static class DemoServerhandler extends IoHandlerAdapter {
        //会话(session)创建之后，回调该方法
        @Override
        public void sessionCreated(IoSession session) throws Exception {
            super.sessionCreated(session);
        }

        //会话(session)打开之后，回调该方法
        @Override
        public void sessionOpened(IoSession session) throws Exception {
            super.sessionOpened(session);
        }

        //接收到消息时回调这个方法
        @Override
        public void messageReceived(IoSession session, Object message) throws Exception {
            super.messageReceived(session, message);
            String result = message.toString();
            session.write("返回的数据......");
            System.out.println("接收到的数据:" + result);
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
