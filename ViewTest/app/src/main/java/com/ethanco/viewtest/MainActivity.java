package com.ethanco.viewtest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //这是一个测试
        //这又是一个测试
        setContentView(R.layout.activity_main);
        //这是一个测试
        //这又是一个测试
        setContentView(R.layout.activity_main);
    }
}
