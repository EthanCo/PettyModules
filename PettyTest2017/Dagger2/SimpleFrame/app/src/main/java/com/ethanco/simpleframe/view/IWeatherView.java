package com.ethanco.simpleframe.view;

import com.ethanco.simpleframe.bean.Weather;

/**
 * Created by EthanCo on 2016/4/4.
 */
public interface IWeatherView extends IWaitDialogOper {
    void loadWeatherSuccess(Weather weather);

    void loadWeatherFaild(String localizedMessage);

}
