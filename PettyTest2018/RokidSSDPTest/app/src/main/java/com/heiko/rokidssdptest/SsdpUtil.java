package com.heiko.rokidssdptest;

/**
 * SSDP 工具类
 *
 * @author EthanCo
 * @since 2018/3/14
 */

public class SsdpUtil {
    public static String getIP(String str) {
        String[] arr = str.split(":");
        if (arr.length >= 2) {
            return arr[0];
        }
        return "";
    }

    public static int getPort(String str) {
        String[] arr = str.split(":");
        if (arr.length >= 2) {
            return Integer.parseInt(arr[1]);
        }
        return -1;
    }
}
