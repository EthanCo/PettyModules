package com.heiko.retrofit2test;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Retrofit API Service
 *
 * @author EthanCo
 * @since 2017/9/4
 */

public interface APIService {

    @GET("weather")
    Call<Weather> loadWeather(@Query("cityname") String cityname, @Query("key") String apiKey);
    /**
     * retrofit 支持 rxjava 整合
     * 这种方法适用于新接口
     */
    @GET("weather")
    Observable<Weather> getWeatherData(@Query("cityname") String cityname, @Query("key") String apiKey);
}
