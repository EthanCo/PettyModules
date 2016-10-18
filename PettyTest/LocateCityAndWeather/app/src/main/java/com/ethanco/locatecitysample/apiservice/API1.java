package com.ethanco.locatecitysample.apiservice;

import com.ethanco.locatecitysample.response.WeatherResponse;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * @Description API
 * Created by EthanCo on 2016/10/9.
 */

public interface API1 {

    //获取天气 from https://www.juhe.cn/docs/api/id/73
    @GET("weather/query")
    Observable<WeatherResponse> loadWeather(@Query("cityname") String cityname, @Query("key") String key);
}
