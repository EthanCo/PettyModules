package com.ethanco.locatecitysample.apiservice;


import com.ethanco.locatecitysample.response.CityResponse;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * @Description API
 * Created by EthanCo on 2016/10/9.
 */

public interface API2 {

    //定位城市 from https://www.juhe.cn/docs/api/id/1
    @GET("ip2addr")
    Observable<CityResponse> locateCity(@Query("ip") String ip, @Query("key") String key);
}
