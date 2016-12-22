package com.ethanco.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import org.apache.log4j.Logger;

//log4J是一个第三方的日志工具类，使用方式类似于Java1.4自带的Logger
//http://www.cnblogs.com/minyc/p/myc201606081439.html
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //加载配置
        ConfigureLog4J configureLog4J = new ConfigureLog4J();
        configureLog4J.configure();
        //初始化 log
        Logger log = Logger.getLogger(this.getClass());
        //写 info 日志
        log.info("不知道呀就是测试一下啊");
    }
}
