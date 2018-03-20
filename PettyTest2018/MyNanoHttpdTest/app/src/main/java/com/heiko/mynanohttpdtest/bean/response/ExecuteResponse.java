package com.heiko.mynanohttpdtest.bean.response;

import java.util.HashMap;
import java.util.Map;

/**
 *
 *
 * @author EthanCo
 * @since 2018/3/15
 */

public class ExecuteResponse {
    /**
     * status : 0
     * data : {"switch":"on"}
     */

    private int status;
    private Map<String, Object> data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void putData(String key, Object value) {
        if (data == null) {
            data = new HashMap<>();
        }
        data.put(key, value);
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }
}
