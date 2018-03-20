package com.heiko.mynanohttpdtest;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import com.google.gson.Gson;
import com.heiko.mynanohttpdtest.bean.response.ExecuteResponse;
import com.heiko.mynanohttpdtest.bean.request.ExecuteRequest;
import com.heiko.mynanohttpdtest.bean.response.ListResponse;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    private Gson gson = new Gson();
    public static final String TAG = "Z-Test";

    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.heiko.mynanohttpdtest", appContext.getPackageName());
    }

    @Test
    public void testListResponse() {
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

        Log.i("Z-Test", "dataBeans:" + gson.toJson(response));
    }

    @Test
    public void testExecuteRequest() {
        String json = "{\"device\": {\"deviceId\": \"xxx\",\"state\": {\"switch\": \"on\"},\"userAuth\": {\"userId\": \"\",\"userToken\": \"\"}},\"action\": {\"property\": \"switch\",\"name\": \"on\"}}";
        ExecuteRequest request = gson.fromJson(json, ExecuteRequest.class);
        Log.i("Z-Test", "request:" + gson.toJson(request));
    }

    @Test
    public void testExecuteResponse() {
        ExecuteResponse response = new ExecuteResponse();
        response.setStatus(0);
        response.putData("switch", "on");
        Log.i(TAG, "response:" + gson.toJson(response));
    }
}
