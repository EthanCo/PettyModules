package com.ethanco.simpleframe.model.service;


import com.ethanco.simpleframe.bean.Weather;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by EthanCo on 2016/4/4.
 */
public interface ApiService {
    @GET("weather/query")
    Observable<Weather> loadWeather(@Query("cityname") String cityname, @Query("key") String key);
}
