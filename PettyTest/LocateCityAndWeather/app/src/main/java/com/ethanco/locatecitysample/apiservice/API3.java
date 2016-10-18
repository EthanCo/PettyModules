package com.ethanco.locatecitysample.apiservice;


import com.ethanco.locatecitysample.response.City2Response;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * @Description API
 * Created by EthanCo on 2016/10/9.
 */

public interface API3 {

    //定位城市 from http://lbsyun.baidu.com/index.php?title=webapi/ip-api
    @GET("ip")
    Observable<City2Response> locateCity(@Query("ip") String ip, @Query("ak") String ak);
}
