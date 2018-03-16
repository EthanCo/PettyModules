package com.heiko.rokidssdptest;

import android.util.Log;

import java.util.Map;

import static com.heiko.rokidssdptest.Consts.COLON;
import static com.heiko.rokidssdptest.Consts.COLON_SPACE;
import static com.heiko.rokidssdptest.Consts.KEY_HEAD;
import static com.heiko.rokidssdptest.Consts.NEWLINE;

/**
 * SSDP 格式 组装
 * --------------------
 * 将特定的Map<String,Object>集合转化为SSDP 报文
 *
 * @author EthanCo
 * @since 2018/3/13
 */

public class SsdpAssembly {

    public static final String TAG = "Z-SsdpAssembly";

    public static String generateDatagram(Map<String, Object> map) {
        StringBuilder sb = new StringBuilder();
        String key;
        Object value;
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            key = entry.getKey();
            value = entry.getValue();
            Log.i(TAG, "generateDatagram: Key = " + key + ", Value = " + value);
            if (KEY_HEAD.startsWith(key)) {
                sb.append(value.toString()).append(NEWLINE);
            } else {
                sb.append(key).append(COLON).append(value).append(NEWLINE);
            }
        }
        return sb.toString();
    }

    public static String generateDatagramIncludeSpace(Map<String, Object> map) {
        StringBuilder sb = new StringBuilder();
        String key;
        Object value;
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            key = entry.getKey();
            value = entry.getValue();
            Log.i(TAG, "generateDatagram: Key = " + key + ", Value = " + value);
            if (KEY_HEAD.startsWith(key)) {
                sb.append(value.toString()).append(NEWLINE);
            } else {
                sb.append(key).append(COLON_SPACE).append(value).append(NEWLINE);
            }
        }
        return sb.toString();
    }
}
