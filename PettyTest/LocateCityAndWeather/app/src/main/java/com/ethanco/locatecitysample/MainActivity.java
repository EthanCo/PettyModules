package com.ethanco.locatecitysample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.ethanco.locatecitysample.apiservice.API1;
import com.ethanco.locatecitysample.apiservice.API2;
import com.ethanco.locatecitysample.apiservice.API3;
import com.ethanco.locatecitysample.utils.NetWorkUtil;
import com.nbhope.hopelauncher.lib.network.NetFacade;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "Z-Main";

    API1 api1 = NetFacade.getInstance().provideService(Constants.URL_JUHE_WEATHER, API1.class);
    API2 api2 = NetFacade.getInstance().provideService(Constants.URL_JUHE_LOCATE_CITY, API2.class);
    API3 api3 = NetFacade.getInstance().provideService(Constants.URL_BAIDU_LOCATE_CITY, API3.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //通过聚合数据定位API城市
        locateCityFromJuHe();
        //通过百度地图定位API城市
        locateCityFromBaidu();

        //通过聚合数据获取天气
        loadWeatherFromJuHe("杭州市");
    }

    private void locateCityFromJuHe() {
        getOuterIP().flatMap(outerIp -> api2.locateCity(outerIp, Constants.KEY_JUHE_LOCATE_CITY))
                .map(response -> response.getResult().getArea())
                .map(area -> area.substring(area.indexOf("省") + 1, area.indexOf("市") + 1))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(city -> Log.i(TAG, "locateCityFromJuHe city: " + city),
                        throwable -> Log.e(TAG, "locateCityFromJuHe error: " + throwable.getMessage()),
                        () -> {
                        });
    }

    private void locateCityFromBaidu() {
        getOuterIP().flatMap(outerIp -> api3.locateCity(outerIp, Constants.KEY_BAIDU_LOCATE_CITY))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(response -> Log.i(TAG, "locateCityFromBaidu city: " +
                                response.getContent().getAddress_detail().getCity()),
                        throwable -> Log.i(TAG, "locateCityFromBaidu error: " + throwable.getMessage()),
                        () -> {
                        });
    }

    private void loadWeatherFromJuHe(String city) {
        api1.loadWeather(city, Constants.KEY_JUHE_WEATHER)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(response -> Log.i(TAG, "loadWeatherFromJuHe weather: " +
                                response.getResult().getData().getWeather().get(0).getInfo()),
                        throwable -> Log.i(TAG, "loadWeatherFromJuHe error: " + throwable.getMessage()),
                        () -> {
                        });
    }

    //获取外网IP
    private Observable<String> getOuterIP() {
        return Observable.just(null).map(o -> NetWorkUtil.getOuterNetFormCmyIP());
    }
}
