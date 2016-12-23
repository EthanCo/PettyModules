package com.ethanco.discorver;

import android.net.nsd.NsdManager;
import android.net.nsd.NsdServiceInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

//mDNS Android使用
//https://developer.android.google.cn/reference/android/net/nsd/NsdManager.html#registerService(android.net.nsd.NsdServiceInfo, int, android.net.nsd.NsdManager.RegistrationListener)
////http://blog.csdn.net/gophers/article/details/37968501
//发现 (Client)
public class MainActivity extends AppCompatActivity implements View.OnClickListener, NsdManager.DiscoveryListener {
    public static final String TAG = "Z-Discorver";
    private NsdManager mNsdManager;
    public static final String SERVICE_TYPE = "_http._tcp"; //_ipp._tcp、_socket._tcp.local、_socket._tcp
    public static final String SERVICE_NAME = "MyService";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNsdManager = (NsdManager) getSystemService(NSD_SERVICE);
        Button btnDiscover = findView(R.id.btn_discover);
        btnDiscover.setOnClickListener(this);
    }

    public <T extends View> T findView(int id) {
        return (T) findViewById(id);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_discover:
                mNsdManager.discoverServices(SERVICE_TYPE, NsdManager.PROTOCOL_DNS_SD, this);
                break;
            default:
        }
    }

    @Override
    public void onStartDiscoveryFailed(String s, int i) {
        Log.i(TAG, "onStartDiscoveryFailed:" + s);
    }

    @Override
    public void onStopDiscoveryFailed(String s, int i) {
        Log.i(TAG, "onStopDiscoveryFailed:" + s);
    }

    @Override
    public void onDiscoveryStarted(String s) {
        Log.i(TAG, "onDiscoveryStarted:" + s);
    }

    @Override
    public void onDiscoveryStopped(String s) {
        Log.i(TAG, "onDiscoveryStopped:" + s);
    }

    @Override
    public void onServiceFound(NsdServiceInfo nsdServiceInfo) {
        Log.i(TAG, "onServiceFound:");

        //FIXME 可能被回调多次
        if (nsdServiceInfo.getServiceName().contains(SERVICE_NAME)) {
            Log.d(TAG, SERVICE_NAME + " device found");
            mNsdManager.resolveService(nsdServiceInfo, new NsdManager.ResolveListener() {
                @Override
                public void onResolveFailed(NsdServiceInfo nsdServiceInfo, int i) {
                    Log.i(TAG, "onResolveFailed");
                }

                @Override
                public void onServiceResolved(NsdServiceInfo nsdServiceInfo) {
                    String info = "onServiceResolved host:" + nsdServiceInfo.getHost().toString() + " port:" + nsdServiceInfo.getPort();
                    Log.i(TAG, info);
                    Toast.makeText(MainActivity.this, info, Toast.LENGTH_SHORT).show();
                    stopServiceDiscovery();
                }
            });
        }
    }

    @Override
    public void onServiceLost(NsdServiceInfo nsdServiceInfo) {
        Log.i(TAG, "onServiceLost:");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopServiceDiscovery();
    }

    //停止发现
    private void stopServiceDiscovery() {
        mNsdManager.stopServiceDiscovery(this);
    }
}
