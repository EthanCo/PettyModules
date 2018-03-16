package com.heiko.rokidssdptest;

/**
 * 常量
 *
 * @author EthanCo
 * @since 2018/3/13
 */

public class Consts {
    public static final String NEWLINE = "\r\n";
    public static final String COLON = ":";
    public static final String COLON_SPACE = ": ";
    public static final String IP = "239.255.255.250";
    public static final int PORT = 1900;
    public static final String HEAD_SEARCH = "M-SEARCH * HTTP/1.1";
    public static final String HEAD_NOTIFY = "NOTIFY * HTTP/1.1";
    public static final String HEAD_OK = "HTTP/1.1 200 OK";

    public static final String KEY_HEAD = "DATAGRAM_HEADER";
    public static final String KEY_HOST = "HOST";
    public static final String KEY_ST = "ST";
    public static final String KEY_MAN = "MAN";
    public static final String KEY_MX = "MX";
    public static final String KEY_UDP_LOCATION = "UDP_LOCATION";

    public static final String KEY_NT = "NT";
    public static final String KEY_NTS = "NTS";
    public static final String KEY_USN = "USN";
    public static final String KEY_LOCATION = "LOCATION";
    public static final String KEY_CACHE_CONTROL = "CACHE-CONTROL";
    public static final String KEY_DEVICE_TYPE = "DEVICE_TYPE";
    public static final String KEY_SERVER = "SERVER";

    //固定值
    public static final String HOMEBASE_DEVICE = "homebase:device";
    public static final String SSDP_ALIVE = "ssdp:alive";
    public static final String SSDP_DISCOVER = "ssdp:discover";
    public static final String UPN_P_HOMEBASE_SSDP = "UPnP/1.1 homebase-ssdp/1.0.0";
}
