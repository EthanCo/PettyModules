package com.ethanco.locatecitysample.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 网络状态
 */
public class NetWorkUtil {

    /**
     * 获取获取外网外网地址  需在异步线程中访问
     * @param ipaddr 提供外网服务的服务器ip地址
     * @return       外网IP
     */
    public static String GetOuterNetIp(String ipaddr) {
        URL infoUrl = null;
        InputStream inStream = null;
        try {
            infoUrl = new URL(ipaddr);
            URLConnection connection = infoUrl.openConnection();
            HttpURLConnection httpConnection = (HttpURLConnection) connection;
            int responseCode = httpConnection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                inStream = httpConnection.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inStream, "utf-8"));
                StringBuilder strber = new StringBuilder();
                String line = null;
                while ((line = reader.readLine()) != null)
                    strber.append(line + "\n");
                inStream.close();
                return strber.toString();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 通过CmyIP获取获取外网外网地址  需在异步线程中访问
     * @return 外网IP
     */
    public static String getOuterNetFormCmyIP() {
        String response = NetWorkUtil.GetOuterNetIp("http://www.cmyip.com/");
        Pattern pattern = Pattern
                .compile("((?:(?:25[0-5]|2[0-4]\\d|((1\\d{2})|([1-9]?\\d)))\\.){3}(?:25[0-5]|2[0-4]\\d|((1\\d{2})|([1-9]?\\d))))");
        Matcher matcher = pattern.matcher(response.toString());
        if (matcher.find()) {
            String group = matcher.group();
            return group;
        }

        return "";
    }
}
