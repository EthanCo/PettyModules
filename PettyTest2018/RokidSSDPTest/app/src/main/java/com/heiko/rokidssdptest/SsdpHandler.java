package com.heiko.rokidssdptest;

import android.text.TextUtils;
import android.util.Log;

import java.io.IOException;
import java.net.DatagramPacket;
import java.util.Map;

/**
 * TODO
 *
 * @author EthanCo
 * @since 2018/3/16
 */

public class SsdpHandler {
    private static final String TAG = "Z-SsdpHandler";
    private static final String TAG_ERROR = "Z-SsdpHandler-Error";
    private static final String TAG_ROKID = "Z-SsdpHandler-Rokid";
    private SSDPSocket ssdpSocket;
    private Thread ssdpThread;
    private volatile boolean isStarting;

    private SsdpHandler() {
    }

    private static class SingleTonHolder {
        private static SsdpHandler sInstance = new SsdpHandler();
    }

    public static SsdpHandler getInstance() {
        return SingleTonHolder.sInstance;
    }

    public void start() {
        if (ssdpSocket == null) {
            try {
                ssdpSocket = new SSDPSocket(Consts.IP, Consts.PORT);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (ssdpSocket == null) return;


        if (ssdpThread == null) {
            isStarting = true;
            initSsdpThread();
        } else {
            isStarting = true;
        }
    }

    private void initSsdpThread() {
        ssdpThread = new Thread() {
            @Override
            public void run() {
                super.run();

                Log.i(TAG, "启动SSDP服务器");
                while (true) {
                    Log.i(TAG, "while true");
                    try {
                        DatagramPacket dp = ssdpSocket.receive(); // Here, I only receive the same packets I initially sent above
                        if (isStarting) {
                            final String data = new String(dp.getData()).trim();
                            final String sourceIP = new String(dp.getAddress().toString()).trim();

                            Map<String, Object> map = SsdpParser.parser(data);
                            Log.i(TAG, "map:" + map.toString());
                            String formatData = SsdpAssembly.generateDatagram(map);
                            Log.i(TAG, "formatData:" + formatData);
                            String man = (String) map.get(Consts.KEY_MAN);
                            String st = (String) map.get(Consts.KEY_ST);
                            if (Consts.SSDP_DISCOVER.equalsIgnoreCase(man) &&
                                    Consts.HOMEBASE_DEVICE.equalsIgnoreCase(st)) {
                                Log.i(TAG_ROKID, "收到若琪发送的SSDP:\r\n" + formatData);
                                String udpLocation = (String) map.get(Consts.KEY_UDP_LOCATION);
                                if (TextUtils.isEmpty(udpLocation)) {
                                    return;
                                }
                                String ip = SsdpUtil.getIP(udpLocation);
                                int port = SsdpUtil.getPort(udpLocation);
                                if (!TextUtils.isEmpty(ip) && port >= 0) {
                                    sendUdpResponse(ip, port);
                                }
                            }

                            Log.i(TAG, "接收到的数据为：\n" + data + "\n數據來源IP：" + sourceIP);
                        }else{
                            Log.i(TAG, "isStarting is false");
                        }
                    } catch (IOException e) {
                        Log.e(TAG_ERROR, Log.getStackTraceString(e.getCause()));
                    }
                }
            }
        };
        ssdpThread.start();
    }

    public void stop() {
        if (ssdpThread != null) {
            ssdpThread = null;
        }
        isStarting = false;
    }

    private void sendUdpResponse(String ip, int port) {
        Log.i(TAG_ROKID, "UDP 端口:" + "ip:" + ip + " port:" + port);
        Map<String, Object> searchMap = SsdpFactory.createSearchResponse();
        String sendData = SsdpAssembly.generateDatagram(searchMap);
        //String sendData = SsdpAssembly.generateDatagramIncludeSpace(searchMap);
        Log.i(TAG_ROKID, "udp 发送:\n" + sendData);
        UdpUtil.send(ip, port, sendData);
    }
}
