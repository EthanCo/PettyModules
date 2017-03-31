package com.ethanco.ftpservertest;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import org.apache.ftpserver.FtpServer;
import org.apache.ftpserver.FtpServerFactory;
import org.apache.ftpserver.ftplet.FtpException;
import org.apache.ftpserver.listener.ListenerFactory;
import org.apache.ftpserver.usermanager.PropertiesUserManagerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Collections;
import java.util.List;

public class MainActivity extends Activity {

    private static final String TAG = "FtpServerService";
    private static String hostip = "191.167.10.53"; // 本机IP
    private static final int PORT = 2222;
    // sd卡目录
    @SuppressLint("SdCardPath")
    private static final String dirname = "/mnt/sdcard/ftp";
    // ftp服务器配置文件路径
    private static final String filename = dirname + "/users.properties";
    private FtpServer mFtpServer = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //创建服务器配置文件
        try {
            creatDirsFiles();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 创建服务器配置文件
     */
    private void creatDirsFiles() throws IOException {
        File dir = new File(dirname);
        if (!dir.exists()) {
            dir.mkdir();
        }
        FileOutputStream fos = null;
        String tmp = getString(R.string.user);
        //String tmp = "user_temp";
        File sourceFile = new File(dirname + "/users.properties");
        fos = new FileOutputStream(sourceFile);
        fos.write(tmp.getBytes());
        if (fos != null) {
            fos.close();
        }
    }

    /**
     * 开启FTP服务器
     *
     * @param hostip 本机ip
     */
    private void startFtpServer(String hostip) {
        FtpServerFactory serverFactory = new FtpServerFactory();

        PropertiesUserManagerFactory userManagerFactory = new PropertiesUserManagerFactory();
        File files = new File(filename);
        //设置配置文件
        userManagerFactory.setFile(files);
        serverFactory.setUserManager(userManagerFactory.createUserManager());
        // 设置监听IP和端口号
        ListenerFactory factory = new ListenerFactory();
        factory.setPort(PORT);
        factory.setServerAddress(hostip);

        // replace the default listener
        serverFactory.addListener("default", factory.createListener());

        // start the server
        mFtpServer = serverFactory.createServer();
        try {
            mFtpServer.start();
            Log.d(TAG, "开启了FTP服务器  ip = " + hostip);
        } catch (FtpException e) {
            System.out.println(e);
        }
    }

    /**
     * 关闭FTP服务器
     */
    private void stopFtpServer() {
        if (mFtpServer != null) {
            mFtpServer.stop();
            mFtpServer = null;
            Log.d(TAG, "关闭了FTP服务器 ip = " + hostip);
        }
    }

    /**
     * 获取本机ip
     */
    private String getLocalIpAddress() {
        try {
            List<NetworkInterface> interfaces = Collections
                    .list(NetworkInterface.getNetworkInterfaces());
            for (NetworkInterface intf : interfaces) {
                List<InetAddress> addrs = Collections.list(intf
                        .getInetAddresses());
                for (InetAddress addr : addrs) {
                    if (!addr.isLoopbackAddress()) {
                        String sAddr = addr.getHostAddress().toUpperCase();
                        //boolean isIPv4 = InetAddressUtils.isIPv4Address(sAddr);
                        //if (isIPv4) {
                        return sAddr;
                        //}
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }


    public void onStartServer(View view) {
        new Thread() {
            @Override
            public void run() {
                //hostip = getLocalIpAddress();
                hostip = Utility.getLocalIPString();
                Log.d(TAG, "获取本机IP = " + hostip);
                startFtpServer(hostip);
            }
        }.start();
    }
}