package com.ethanco.mdnstest;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

//mDNS Android使用
//https://developer.android.google.cn/reference/android/net/nsd/NsdManager.html#registerService(android.net.nsd.NsdServiceInfo, int, android.net.nsd.NsdManager.RegistrationListener)
//http://blog.csdn.net/gophers/article/details/37968501
public class MainActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
