package com.lib.ftpserver;

import android.content.Context;
import android.util.Log;

import org.apache.ftpserver.FtpServer;
import org.apache.ftpserver.FtpServerFactory;
import org.apache.ftpserver.ftplet.FtpException;
import org.apache.ftpserver.listener.ListenerFactory;
import org.apache.ftpserver.usermanager.PropertiesUserManagerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Ftp 工具类
 *
 * @author EthanCo
 * @since 2017/4/21
 */

public class FtpDirector {

    private static final String TAG = "FtpServerService";
    private Context context;
    private String hostIP;
    private int port;
    private String ftpDir = "";
    // ftp服务器配置文件路径
    private String configPath;
    private FtpServer mFtpServer = null;


    public FtpDirector(Context context, int port) {
        this(context, port, Util.getCustomDir(context, "ftp"), Util.getCustomDir(context, "ftp"));
    }

    /**
     * @param context
     * @param port      端口
     * @param ftpDir    ftp文件夹路径
     * @param configDir 配置文件夹路径
     */
    public FtpDirector(Context context, int port, String ftpDir, String configDir) {
        this.context = context;
        this.port = port;
        this.ftpDir = ftpDir;
        this.configPath = configDir + "/users.properties";
        Log.i(TAG, "ftpDir:" + ftpDir + " configPath:" + configPath);
        createServerConfigFile();
    }

    /**
     * 创建服务器配置文件
     */
    private void createServerConfigFile() {
        try {
            createDirsFiles(context);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 创建服务器配置文件
     */
    private void createDirsFiles(Context context) throws IOException {
        File ftpFolder = new File(ftpDir);
        if (!ftpFolder.exists()) {
            ftpFolder.mkdirs();
        }

        String configDir = configPath.substring(0, configPath.lastIndexOf("/") + 1);
        File configFolder = new File(configDir);
        if (!configFolder.exists()) {
            configFolder.mkdirs();
        }

        String user = String.format(context.getString(R.string.user), ftpDir, ftpDir);
        File sourceFile = new File(configPath);
        FileOutputStream fos = new FileOutputStream(sourceFile);
        fos.write(user.getBytes());
        if (fos != null) {
            fos.close();
        }
    }

    /**
     * 开启FTP服务器
     *
     * @param hostIP 本机ip
     */
    private void startFtpServer(String hostIP) {
        FtpServerFactory serverFactory = new FtpServerFactory();

        PropertiesUserManagerFactory userManagerFactory = new PropertiesUserManagerFactory();
        File files = new File(configPath);
        //设置配置文件
        userManagerFactory.setFile(files);
        serverFactory.setUserManager(userManagerFactory.createUserManager());
        // 设置监听IP和端口号
        ListenerFactory factory = new ListenerFactory();
        factory.setPort(port);
        factory.setServerAddress(hostIP);

        // replace the default listener
        serverFactory.addListener("default", factory.createListener());

        // start the server
        mFtpServer = serverFactory.createServer();
        try {
            mFtpServer.start();
            Log.d(TAG, "开启了FTP服务器  ip = " + hostIP);
        } catch (FtpException e) {
            System.out.println(e);
        }
    }

    /**
     * 关闭FTP服务器
     */
    public void stopServer() {
        if (mFtpServer != null) {
            mFtpServer.stop();
            mFtpServer = null;
            Log.d(TAG, "关闭了FTP服务器 ip = " + hostIP);
        }
    }

    /**
     * 启动FTP服务器
     */
    public void startServer() {
        new Thread() {
            @Override
            public void run() {
                hostIP = Util.getLocalIPString();
                Log.d(TAG, "获取本机IP = " + hostIP);
                startFtpServer(hostIP);
            }
        }.start();
    }
}
