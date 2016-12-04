package com.ethanco.mimaservice;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
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
        IoAcceptor acceptor = new NioSocketAcceptor();
        //得到Mima为我们提供的默认的日志过滤器
        acceptor.getFilterChain().addLast("Logger",new LoggingFilter());
        //添加Codec过滤器
        acceptor.getFilterChain().addLast("codec",new ProtocolCodecFilter(new ObjectSerializationCodecFactory()));
        //设置事件处理Handler
        acceptor.setHandler(new DemoServerhandler());
        //设置读缓存区大小
        acceptor.getSessionConfig().setReadBufferSize(2048);
        //设置空闲时间 10后没有任何读写，回到空闲状态
        //IdleStatus.BOTH_IDLE 读和写 IdleStatus.READER_IDLE 读 IdleStatus.WRITER_IDLE 写
        acceptor.getSessionConfig().setIdleTime(IdleStatus.WRITER_IDLE,10);
        //开始监听客户端的连接 端口为9123
        try {
            acceptor.bind(new InetSocketAddress(9123));
        } catch (Exception e) {

        }
    }

    //负责session对象的创建监听以及小学发送的接收监听
    private static class DemoServerhandler extends IoHandlerAdapter{

    }
}
