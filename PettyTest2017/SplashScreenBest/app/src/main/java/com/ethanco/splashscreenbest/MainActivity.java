package com.ethanco.splashscreenbest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/**
 * 闪屏页的更好的实现方式
 * 详情看 http://mp.weixin.qq.com/s/qRI--v-7tpu4TjqKCrq3og
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppTheme);
        setContentView(R.layout.activity_main);
    }
}
