package com.ethanco.register;

import android.net.nsd.NsdManager;
import android.net.nsd.NsdServiceInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.net.InetAddress;
import java.net.UnknownHostException;

//mDNS Android使用
//https://developer.android.google.cn/reference/android/net/nsd/NsdManager.html#registerService(android.net.nsd.NsdServiceInfo, int, android.net.nsd.NsdManager.RegistrationListener)
////http://blog.csdn.net/gophers/article/details/37968501
//注册 (Service)
public class MainActivity extends AppCompatActivity implements NsdManager.RegistrationListener {
    public static final String SERVICE_TYPE = "_http._tcp"; //_ipp._tcp、_socket._tcp.local、_socket._tcp
    public static final String TAG = "Z-Register";
    public static final String SERVICE_NAME = "MyService";

    private NsdManager mNsdManager;
    private TextView tvInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvInfo = findView(R.id.tv_info);

        mNsdManager = (NsdManager) getSystemService(NSD_SERVICE);
        NsdServiceInfo serviceInfo = new NsdServiceInfo();
        serviceInfo.setServiceName(SERVICE_NAME);
        serviceInfo.setServiceType(SERVICE_TYPE);
        serviceInfo.setPort(5123);
        InetAddress addressHost = null;
        try {
            String wifiIp = getWifiIp();
            Log.i(TAG, "本机局域网IP:" + wifiIp);
            addressHost = InetAddress.getByName(wifiIp);
            serviceInfo.setHost(addressHost);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        mNsdManager.registerService(serviceInfo, NsdManager.PROTOCOL_DNS_SD, this);
        setTextInfo("正在注册...");
    }

    private String getWifiIp() {
        WifiManager wifiManager = (WifiManager) getSystemService(WIFI_SERVICE);
        WifiInfo wifiInfo = wifiManager.getConnectionInfo();
        int ipAddress = wifiInfo.getIpAddress();

        return intToIp(ipAddress);
    }

    @Override
    public void onRegistrationFailed(NsdServiceInfo nsdServiceInfo, int i) {
        Log.i(TAG, "onRegistrationFailed");
    }

    @Override
    public void onUnregistrationFailed(NsdServiceInfo nsdServiceInfo, int i) {
        Log.i(TAG, "onUnregistrationFailed");
    }

    @Override
    public void onServiceRegistered(NsdServiceInfo nsdServiceInfo) {
        Log.i(TAG, "onServiceRegistered");
        setTextInfo("注册成功");
    }

    @Override
    public void onServiceUnregistered(NsdServiceInfo nsdServiceInfo) {
        Log.i(TAG, "onServiceUnregistered");
        setTextInfo("注册失败");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mNsdManager.unregisterService(this);
    }

    private void setTextInfo(final String info) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                tvInfo.setText(info);
            }
        });
    }

    public <T extends View> T findView(int id) {
        return (T) findViewById(id);
    }

    public String intToIp(int ipInt) {
        return new StringBuilder()
                .append((ipInt & 0xff)).append('.')
                .append((ipInt >> 8) & 0xff).append('.')
                .append((ipInt >> 16) & 0xff).append('.')
                .append(((ipInt >> 24) & 0xff))
                .toString();
    }
}
