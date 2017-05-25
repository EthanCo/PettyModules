package com.nbhope.hopelauncher.lib.network;


import com.nbhope.hopelauncher.lib.network.bean.request.CmdRequest;
import com.nbhope.hopelauncher.lib.network.bean.response.TimeResponse;

import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by EthanCo on 2015/6/13.
 */
public interface APIService {
    /**
     * 获取服务器信息 (之后调用了该方法后，才可调用login方法登录)
     *
     * @param cmd
     * @return
     */
    @POST("api")
    Observable<TimeResponse> getServerTime(@Body CmdRequest cmd);

    @POST("experience")
    Observable<String> audition(@Body String body);


}
