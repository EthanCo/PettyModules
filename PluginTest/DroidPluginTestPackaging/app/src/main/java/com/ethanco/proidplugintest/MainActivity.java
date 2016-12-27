package com.ethanco.proidplugintest;

import android.os.Bundle;

import com.ethanco.proidplugintest.base.BaseFragmentActivity;

/**
 * DroidPlugin 插件化 封装后的Demo
 */
public class MainActivity extends BaseFragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        changeFragment(MainFragment.newInstance(), R.id.layout_container);
    }
}
