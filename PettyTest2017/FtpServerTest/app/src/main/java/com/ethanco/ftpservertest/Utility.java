package com.ethanco.ftpservertest;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.util.Base64;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.nio.charset.Charset;
import java.util.Enumeration;

/**
 * Created by hult on 2016/5/5.
 * 工具类
 */
public class Utility {

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
     * byte[]转int
     *
     * @param b
     * @param offset
     * @return
     */
    public static int byteArrayToInt(byte[] b, int offset) {
        int value = 0;
        for (int i = 0; i < 4; i++) {
            int shift = (4 - 1 - i) * 8;
            value += (b[i + offset] & 0x000000FF) << shift;
        }
        return value;
    }

    /**
     * 通过byte数组取到short
     *
     * @param b
     * @param index 第几位开始取
     * @return
     */
    public static short getShort(byte[] b, int index) {
        return (short) (((b[index + 1] << 8) | b[index + 0] & 0xff));
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
     * 获取mac地址
     * @param context
     * @return
     */
    public static String getMacAddress(Context context){
        WifiManager wifi = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        WifiInfo info = wifi.getConnectionInfo();
        return info.getMacAddress();
    }

    /**
     * 数据复制
     *
     * @param buffer 数据
     * @param offset 开始位置
     * @param len    长度
     * @return 数据
     */
    public static byte[] bytesCopy(byte[] buffer, int offset, int len) {
        byte[] result = new byte[len];
        System.arraycopy(buffer, offset, result, 0, len);
        return result;
    }


    /**
     * 字节组转为16进制字符串形式显示
     *
     * @param buffer 字节组
     * @param start  开始位置
     * @param length 长度
     * @return String
     */
    public static String bytesToHexString(byte[] buffer, int start, int length) {
        String result = "";
        if (buffer != null && buffer.length > start) {
            result = String.format("%1$02X", buffer[start]);
            for (int i = start + 1; i < start + length && i < buffer.length; i++) {
                result += " " + String.format("%1$02X", buffer[i]);
            }
        }
        return result;
    }


    /**
     * 字节组转为字符串
     *
     * @param buffer 字节组
     * @param start  开始位置
     * @param length 长度
     * @return String
     */
    public static String bytesToAsciiString(byte[] buffer, int start, int length) {
        char[] result = new char[length];
        if (buffer == null || start + length > buffer.length) {
            return "";
        }
        for (int i = 0; i < length; i++) {
            result[i] = (char) byteToInt(buffer[start + i]);
        }
        return new String(result).trim();
    }

    /**
     * 字节组转为字符串
     *
     * @param buffer 字节组
     * @return String
     */
    public static String bytesToAsciiString(byte[] buffer) {
        return bytesToAsciiString(buffer, 0, buffer.length);
    }

    /**
     * 字节组转为字符串
     *
     * @param buffer 字节组
     * @param start  开始位置
     * @param length 长度
     * @return String
     */
    public static String bytesToString(byte[] buffer, int start, int length) {
        int len = 0;
        if (buffer == null || start + length > buffer.length) {
            return "";
        }
        while (buffer[start + len] != 0) {
            len++;
            if (len == length)
                break;
        }
        return new String(buffer, start, len);
    }


    /**
     * CRC 16校验
     *
     * @param buf
     * @param start
     * @param length
     * @return
     */
    public static int CRC16(byte[] buf, int start, int length) {
        int crc = 0;
        int ccitt16 = 0x1021;
        int i, Len;
        for (Len = 0; Len < length; Len++) {
            crc = (crc ^ (byteToInt(buf[Len + start]) << 8)) & 0xffff;
                /* 新的数据与将原来的余数（就是crc）相加（加法就是异或操作） */
                /* 求数据的CRC校验码 */
            for (i = 0; i < 8; i++) {
                if ((crc & 0x8000) != 0) /* 最高位为1,减去除数 */ {
                    crc = (crc << 1) & 0xffff;
                    crc = (crc ^ ccitt16) & 0xffff;
                } else /* 最高位为0,不需要减去除数 */ {
                    crc = (crc << 1) & 0xffff; /* 直接移位 */
                }
            }
        }
        return crc;
    }

    /**
     * 转字符串
     *
     * @param base64String
     * @return
     */
    public static String base64StringToAsciiString(String base64String) {
        String result = "";
        if (base64String.length() > 0) {
            byte[] temp = base64StringToBytes(base64String);
            result = new String(temp, Charset.forName("US-ASCII"));
        }
        return result;
    }

    /**
     * base64 转 字节组
     *
     * @param base64String
     * @return
     */
    private static byte[] base64StringToBytes(String base64String) {
        return base64_decode(base64String.getBytes(Charset.forName("US-ASCII")));
    }

    /**
     * base64 解码
     *
     * @param temp
     * @return
     */
    public static byte[] base64_decode(byte[] temp) {
        return Base64.decode(temp, Base64.DEFAULT);
    }


    /**
     * 转数值
     *
     * @param hexString
     * @return
     */
    public static int hexStringToInt(String hexString) throws Exception {
        int result = 0;

        if (hexString == null || hexString.length() == 0 || hexString.length() > 8)
            throw new Exception("无效的数值");

        if (hexString.length() % 2 == 0) {
            result = Integer.parseInt(hexString, 16);
        }
        return result;
    }

    /**
     * int 转 byte[]
     *
     * @param i
     * @return
     */
    public static byte[] intToByteArray(int i) {
        byte[] result = new byte[4];
        result[0] = (byte) ((i >> 24) & 0xFF);
        result[1] = (byte) ((i >> 16) & 0xFF);
        result[2] = (byte) ((i >> 8) & 0xFF);
        result[3] = (byte) (i & 0xFF);
        return result;
    }

    /**
     * 转字符串
     *
     * @param base64String
     * @return
     */
    public static String base64StringToUTF8String(String base64String) {
        String result = "";
        if (base64String.length() > 0) {
            byte[] temp = base64StringToBytes(base64String);
            result = new String(temp, Charset.forName("UTF-8"));
            result = result.trim();
        }
        return result;
    }

    /**
     * base64 编码
     *
     * @param temp
     * @return
     */
    public static byte[] base64_encode(byte[] temp) {
        return Base64.encode(temp, Base64.DEFAULT);
    }

    /**
     * base64 编码
     *
     * @param temp
     * @param offset
     * @param len
     * @return
     */
    public static byte[] base64_encode(byte[] temp, int offset, int len) {
        return Base64.encode(temp, offset, len, Base64.DEFAULT);
    }

    public static String intToHexString(int i) {
        return String.format("%1$08X", i);
    }

    /**
     * 转字符串
     *
     * @param utf8String
     * @return
     */
    public static String utf8StringToBase64String(String utf8String) {
        String result = "";
        if (utf8String.length() > 0) {
            byte[] temp = utf8String.getBytes(Charset.forName("UTF-8"));
            return new String(base64_encode(temp));
        }
        return result;
    }
}
