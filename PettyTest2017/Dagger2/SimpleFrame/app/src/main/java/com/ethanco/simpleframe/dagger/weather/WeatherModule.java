package com.ethanco.simpleframe.dagger.weather;

import com.ethanco.simpleframe.view.IWeatherView;
import com.ethanco.simpleframe.viewmodel.WeatherViewModel;

import dagger.Module;
import dagger.Provides;

/**
 * Created by EthanCo on 2016/4/5.
 */
@Module
public class WeatherModule {
    private IWeatherView view;

    public WeatherModule(IWeatherView view) {
        this.view = view;
    }

    @Provides
    public WeatherViewModel proviceWeatherViewModel() {
        return new WeatherViewModel(view);
    }
}
