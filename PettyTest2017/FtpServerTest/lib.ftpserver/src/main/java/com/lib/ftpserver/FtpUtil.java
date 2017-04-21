package com.lib.ftpserver;

import android.content.Context;
import android.os.Environment;

import java.io.File;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

/**
 * Created by hult on 2016/5/5.
 * 工具类
 */
class FtpUtil {

    /**
     * byte 转 int
     *
     * @param b byte
     * @return int
     */
    public static int byteToInt(byte b) {
        return (b + 0x100) % 0x100;
    }

    /**
     * 字节组转IP
     *
     * @param buffer 数据
     * @param offset 偏移位置
     * @return String
     */
    public static String bytesToIPString(byte[] buffer, int offset) {
        return String.format("%1$d.%2$d.%3$d.%4$d", byteToInt(buffer[offset]),
                byteToInt(buffer[offset + 1]), byteToInt(buffer[offset + 2]), byteToInt(buffer[offset + 3]));
    }

    /**
     * 获取本地地址
     *
     * @return String
     */
    public static String getLocalIPString() {
        byte[] ip = getLocalIP();
        return bytesToIPString(ip, 0);
    }

    /**
     * 获取本地地址
     *
     * @return bytes
     */
    public static byte[] getLocalIP() {
        try {
            for (Enumeration<NetworkInterface> mEnumeration = NetworkInterface
                    .getNetworkInterfaces(); mEnumeration.hasMoreElements(); ) {

                NetworkInterface intf = mEnumeration.nextElement();

                for (Enumeration<InetAddress> enumIPAddress = intf
                        .getInetAddresses(); enumIPAddress.hasMoreElements(); ) {
                    InetAddress inetAddress = enumIPAddress.nextElement();

                    // 如果不是回环地址
                    if (!inetAddress.isLoopbackAddress() && Inet4Address.class.isInstance(inetAddress)) {

                        // 直接返回本地IP地址
                        return inetAddress.getAddress();
                    }
                }
            }
        } catch (SocketException ex) {
            System.err.print("error");
        }
        return new byte[4];
    }

    /**
     * 获取自定义路径 外置存储/folder
     *
     * @param context
     * @param folder  外置存储一级文件夹名
     * @return
     */
    public static String getCustomDir(Context context, String folder) {
        String dir;
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            dir = Environment.getExternalStorageDirectory() + File.separator + folder;
        } else {
            dir = getFileDir(context, folder);
        }
        return dir;
    }

    /**
     * 获取自定义路径 外置存储/folder/folderChild
     *
     * @param context
     * @param folder      外置存储一级文件夹名
     * @param folderChild folder文件夹内的文件夹名
     * @return
     */
    public static String getCustomDir(Context context, String folder, String folderChild) {
        String dir = getCustomDir(context, folder);
        dir += File.separator + folderChild;
        return dir;
    }

    /**
     * 获取 Android/data/包名/File/folder
     *
     * @param context
     * @param folder
     * @return
     */
    public static String getFileDir(Context context, String folder) {
        String dir;
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            dir = context.getExternalFilesDir(folder).getPath();
        } else {
            dir = context.getFilesDir().getPath();
        }
        return dir;
    }
}
