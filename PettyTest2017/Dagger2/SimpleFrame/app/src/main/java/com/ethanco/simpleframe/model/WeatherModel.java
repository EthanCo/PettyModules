package com.ethanco.simpleframe.model;


import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.ethanco.simpleframe.Constants;
import com.ethanco.simpleframe.bean.Weather;
import com.ethanco.simpleframe.frame.utils.ACache;
import com.ethanco.simpleframe.model.service.ApiService;

import java.text.SimpleDateFormat;
import java.util.Date;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by EthanCo on 2016/4/4.
 */
public class WeatherModel extends BaseModel implements IWeatherModel {

    @Override
    public Observable<Weather> getWeather(String cityname) {
        return Observable.concat(getWeatherFromLocal(), getWeatherFromNet(cityname))
                .first(weather -> checkWeather(cityname, weather))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @NonNull
    private Boolean checkWeather(String cityname, Weather weather) {
        if (null != weather) {
            String date = weather.getResult().getData().getRealtime().getDate();
            String city = weather.getResult().getData().getRealtime().getCity_name();
            if (TextUtils.isEmpty(date) || TextUtils.isEmpty(city)) {
                return false;
            }
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            String dateNow = format.format(new Date());
            return date.equals(dateNow) && cityname.equals(city);
        }
        return false;
    }

    private Observable<Weather> getWeatherFromLocal() {
        return Observable.just(getSpWeather())
                .map(s -> {
                            try {
                                return gson.fromJson(s, Weather.class);
                            } catch (Exception e) {
                                return null;
                            }
                        }
                );
    }

    private Observable<Weather> getWeatherFromNet(String cityname) {
        return application.retrofit.create(ApiService.class)
                .loadWeather(cityname, Constants.JUHE_WEATHER_APIKEY)
                .doOnNext(weather -> saveWeather(weather));

    }

    private void saveWeather(Weather weather) {
        aCache.put(Constants.SP_WEATHER_DATA, gson.toJson(weather), ACache.TIME_DAY);
    }

    private String getSpWeather() {
        return aCache.getAsString(Constants.SP_WEATHER_DATA);
    }
}
