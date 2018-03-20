package com.heiko.mynanohttpdtest;

import com.heiko.mynanohttpdtest.bean.response.ListResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO
 *
 * @author EthanCo
 * @since 2018/3/15
 */

public class ResponseFactory {
    public static ListResponse createListResponse() {
        ListResponse response = new ListResponse();
        response.setStatus(0);
        List<ListResponse.DataBean> dataBeans = new ArrayList<>();
        response.setData(dataBeans);

        ListResponse.DataBean dataBean = new ListResponse.DataBean();
        dataBean.setType("speaker");
        dataBean.setDeviceId("330225");
        dataBean.setName("HOPE A7");
        dataBean.putState("media_control", "pause");
        String[] arr = new String[]{"play_media", "pause", "play", "stop", "playmode", "previous", "next", "get_meta"};
        dataBean.putAction("media_control", arr);
        dataBeans.add(dataBean);

        return response;
    }

    /*public static void createExecuteResponse() {
        ExecuteRequest request = new ExecuteRequest();
        request.setDevice();
    }*/
}
