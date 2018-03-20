package com.heiko.mynanohttpdtest;

import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
import com.heiko.mynanohttpdtest.bean.request.ExecuteRequest;
import com.heiko.mynanohttpdtest.bean.request.ListRequest;
import com.heiko.mynanohttpdtest.bean.response.ListResponse;

import java.io.IOException;
import java.util.Map;

import fi.iki.elonen.NanoHTTPD;

/**
 * TODO
 *
 * @author EthanCo
 * @since 2018/3/14
 */
public class AndroidWebServer extends NanoHTTPD {
    private static final String TAG = "Z-AndroidWebServer";
    private Gson gson = new Gson();

    public AndroidWebServer(int port) {
        super(port);
    }

    public AndroidWebServer(String hostname, int port) {
        super(hostname, port);
    }

    //...
    @Override
    public Response serve(IHTTPSession session) {
        String method = session.getMethod().name();
        String uri = session.getUri();
        Map<String, String> map = session.getParms();
        Map<String, String> header = session.getHeaders();
        try {
            session.parseBody(map);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ResponseException e) {
            e.printStackTrace();
        }

        Log.i(TAG, "method:" + method + " uri:" + uri + " map:" + map);
        String params = map.get("params");
        Log.i(TAG, "params:" + params);

        if ("/login".equalsIgnoreCase(uri)) {
            String msg = "<html><body><h1>Hello server</h1>\n";
            Map<String, String> parms = session.getParms();
            if (parms.get("username") == null) {
                msg += "<form action='?' method='get'>\n";
                msg += "<p>Your name: <input type='text' name='username'></p>\n";
                msg += "</form>\n";
            } else {
                msg += "<p>Hello, " + parms.get("username") + "!</p>";
            }
            return newFixedLengthResponse(msg + "</body></html>\n");
        } else if (Consts.METHOD_COMMAND.equalsIgnoreCase(uri)) {

        } else if (Consts.METHOD_EXECUTE.equalsIgnoreCase(uri)) {
            ExecuteRequest request = gson.fromJson("", ExecuteRequest.class);
            Map<String, Object> actions = request.getAction();
            if (actions != null) {
                String play_media = (String) actions.get("play_media");
                String pause = (String) actions.get("pause");
                String play = (String) actions.get("play");
                String stop = (String) actions.get("stop");
                String playMode = (String) actions.get("playmode");
                String previous = (String) actions.get("previous");
                String next = (String) actions.get("next");
                String getMeta = (String) actions.get("get_meta");

                if (!TextUtils.isEmpty(play_media)) {
                    if (play_media.equals("media_tag")) { //媒体源

                    }else if(play_media.equals("singer")){ //歌手

                    }else{//song 歌曲

                    }
                } else if (!TextUtils.isEmpty(pause)) {

                } else if (!TextUtils.isEmpty(play)) {

                } else if (!TextUtils.isEmpty(stop)) {

                } else if (!TextUtils.isEmpty(playMode)) { //播放模式
                    if (playMode.equals("")) {

                    }else if(playMode.equals("")){

                    }else{

                    }
                } else if (!TextUtils.isEmpty(previous)) { //上一首

                } else if (!TextUtils.isEmpty(next)) { //下一首

                } else if (!TextUtils.isEmpty(getMeta)) { //获取当前播放媒体信息

                }
            }
            //Log.i(TAG, "methodExecute:"+);
        } else if (Consts.METHOD_LIST.equals(uri)) {
            String postData = map.get("postData");
            ListRequest request = gson.fromJson(postData, ListRequest.class);
            Log.i(TAG, "ListRequest:" + request.toString());

            //String response = "{\"data\": [{\"deviceId\": \"330225\",\"name\": \"HOPE A7\",\"state\": {\"media_control\": \"pause\"},\"actions\": {\"media_control\": [\"play_media\", \"pause\", \"play\", \"stop\", \"playmode\", \"previous\", \"next\", \"get_meta\"]},\"type\": \"speaker\"}],\"status\": 0}";
            ListResponse response = ResponseFactory.createListResponse();
            Log.i(TAG, "response:" + gson.toJson(response));
            return newFixedLengthResponse(gson.toJson(response));
        }
        String msg = "<html><body><h1>Hello server</h1>\n";
        Map<String, String> parms = session.getParms();
        if (parms.get("username") == null) {
            msg += "<form action='?' method='get'>\n";
            msg += "<p>Your name: <input type='text' name='username'></p>\n";
            msg += "</form>\n";
        } else {
            msg += "<p>Hello, " + parms.get("username") + "!</p>";
        }
        return newFixedLengthResponse(msg + "</body></html>\n");
    }
}
