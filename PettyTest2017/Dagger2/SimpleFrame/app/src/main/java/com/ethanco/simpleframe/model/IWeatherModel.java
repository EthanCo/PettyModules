package com.ethanco.simpleframe.model;


import com.ethanco.simpleframe.bean.Weather;

import rx.Observable;

/**
 * Created by EthanCo on 2016/4/4.
 */
public interface IWeatherModel {
    Observable<Weather> getWeather(String cityname);
}
