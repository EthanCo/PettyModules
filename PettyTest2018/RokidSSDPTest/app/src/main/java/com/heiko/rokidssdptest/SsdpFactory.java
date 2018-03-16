package com.heiko.rokidssdptest;

import java.util.LinkedHashMap;
import java.util.Map;

import static com.heiko.rokidssdptest.Consts.COLON;
import static com.heiko.rokidssdptest.Consts.HEAD_NOTIFY;
import static com.heiko.rokidssdptest.Consts.HOMEBASE_DEVICE;
import static com.heiko.rokidssdptest.Consts.IP;
import static com.heiko.rokidssdptest.Consts.KEY_CACHE_CONTROL;
import static com.heiko.rokidssdptest.Consts.KEY_DEVICE_TYPE;
import static com.heiko.rokidssdptest.Consts.KEY_HEAD;
import static com.heiko.rokidssdptest.Consts.KEY_HOST;
import static com.heiko.rokidssdptest.Consts.KEY_LOCATION;
import static com.heiko.rokidssdptest.Consts.KEY_NT;
import static com.heiko.rokidssdptest.Consts.KEY_NTS;
import static com.heiko.rokidssdptest.Consts.KEY_SERVER;
import static com.heiko.rokidssdptest.Consts.KEY_USN;
import static com.heiko.rokidssdptest.Consts.PORT;
import static com.heiko.rokidssdptest.Consts.SSDP_ALIVE;
import static com.heiko.rokidssdptest.Consts.UPN_P_HOMEBASE_SSDP;

/**
 * SSDP 命令生成工厂
 *
 * @author EthanCo
 * @since 2018/3/13
 */

public class SsdpFactory {
    /**
     * 搜索反馈Response
     * @return
     */
    public static Map<String, Object> createSearchResponse() {
        Map<String, Object> map = new LinkedHashMap<>();
        map.put(KEY_HEAD, HEAD_NOTIFY);
        map.put(KEY_HOST, IP + COLON + PORT);
        map.put(KEY_NT, HOMEBASE_DEVICE);
        map.put(KEY_NTS, SSDP_ALIVE);
        map.put(KEY_USN, "uuid:f40c2981-7329-40b7-8b04-27f187ae0003"); //TODO 使用真正的UUID
        map.put(KEY_LOCATION, "http://192.168.39.101:31550"); //TODO
        map.put(KEY_CACHE_CONTROL, "max-age=1800"); //TODO
        map.put(KEY_DEVICE_TYPE, "bridge"); //TODO bridge single
        return map;
    }

    /**
     * 心跳反馈
     * @return
     */
    public static Map<String, Object> cretaeHeartBeatResponse() {
        Map<String, Object> map = createSearchResponse();
        map.put(KEY_SERVER, UPN_P_HOMEBASE_SSDP);
        return map;
    }

    /**
     * 下线
     * @return
     */
    public static Map<String, Object> createOfflineResponse() {
        Map<String, Object> map = new LinkedHashMap<>();
        map.put(KEY_HEAD, HEAD_NOTIFY);
        map.put(KEY_HOST, IP + COLON + PORT);
        map.put(KEY_NT, HOMEBASE_DEVICE);
        map.put(KEY_NTS, SSDP_ALIVE);
        map.put(KEY_USN, "uuid:f40c2981-7329-40b7-8b04-27f187ae0001");
        map.put(KEY_CACHE_CONTROL, "max-age=1800");
        map.put(KEY_SERVER, UPN_P_HOMEBASE_SSDP);
        return map;
    }
}
