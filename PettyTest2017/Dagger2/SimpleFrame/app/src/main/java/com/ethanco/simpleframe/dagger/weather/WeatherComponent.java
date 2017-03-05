package com.ethanco.simpleframe.dagger.weather;

import com.ethanco.simpleframe.view.WeatherActivity;

import dagger.Component;

/**
 * Created by EthanCo on 2016/4/5.
 */
@Component(modules = WeatherModule.class)
public interface WeatherComponent {
    void inject(WeatherActivity activity);
}
