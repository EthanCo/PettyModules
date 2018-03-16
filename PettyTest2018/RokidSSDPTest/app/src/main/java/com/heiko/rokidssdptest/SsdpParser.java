package com.heiko.rokidssdptest;

import android.text.TextUtils;

import java.util.LinkedHashMap;
import java.util.Map;

import static com.heiko.rokidssdptest.Consts.HEAD_SEARCH;
import static com.heiko.rokidssdptest.Consts.NEWLINE;

/**
 * SSDP 报文解析
 *
 * @author EthanCo
 * @since 2018/3/13
 */
public class SsdpParser {
    public static Map<String, Object> parser(String data) {
        Map<String, Object> map = new LinkedHashMap<>();
        data = data.trim();
        if (data.startsWith(HEAD_SEARCH)) {
            String[] dataArray = data.split(NEWLINE);
            for (String line : dataArray) {
                if (TextUtils.isEmpty(line)) {
                    continue;
                }

                int index = line.indexOf(":");
                if (index < 0) continue;
                String key = line.substring(0, index);
                if (line.length() < index + 1) continue;
                String value = line.substring(index + 1);
                //if (arr == null) return;

                if (!TextUtils.isEmpty(key)) { //!TextUtils.isEmpty(arr[0])
                    System.out.println("arr[0]--->>>" + key);
                }
                if (!TextUtils.isEmpty(value)) { //!TextUtils.isEmpty(arr[1])
                    value = value.trim();
                    if (value.startsWith("\"")) {
                        value = value.substring(1);
                    }
                    if (value.endsWith("\"")) {
                        value = value.substring(0, value.length() - 1);
                    }
                    System.out.println("arr[1]--->>>" + value);
                }
                if (!TextUtils.isEmpty(key) && value != null) {
                    map.put(key, value);
                }

                /*String lineUpper = line.toUpperCase();
                if (lineUpper.startsWith(HEAD_SEARCH)) {
                    map.put(KEY_HEAD, line.replace(NEWLINE, ""));
                    //ssdpBean.setHead(line.replace(NEWLINE, ""));
                } else if (lineUpper.startsWith("HOST:")) {
                    String host = substringOptimize(line, 5);
                    map.put(KEY_HOST, host);
                    //ssdpBean.setHost(host);

                    *//*String[] arr = host.split(":");
                    if (arr.length >= 2) {
                        ssdpBean.setIp(arr[0]);
                        ssdpBean.setPort(Integer.parseInt(arr[1]));
                    }*//*
                } else if (lineUpper.startsWith("ST:".replace(NEWLINE, ""))) {
                    String st = substringOptimize(line, 3);
                    map.put(KEY_ST, st);
                    //ssdpBean.setST(st);
                } else if (lineUpper.startsWith("MAN:")) {
                    String man = substringOptimize(line, 4).replace("\"", "");
                    map.put(KEY_MAN, man);
                    //ssdpBean.setMan(man);
                } else if (lineUpper.startsWith("MX:")) {
                    int mx = Integer.parseInt(substringOptimize(line, 3).replace(" ", ""));
                    map.put(KEY_MX, mx);
                    //ssdpBean.setMX(mx);
                }*/
            }
        }
        return map;
    }

    private static String substringOptimize(String line, int beginIndex) {
        return line.substring(beginIndex).replace(NEWLINE, "").trim();
    }
}
