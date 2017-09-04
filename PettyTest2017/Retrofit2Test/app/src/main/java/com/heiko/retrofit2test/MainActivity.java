package com.heiko.retrofit2test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    //TODO 此API接口会过期，请在http://www.haoservice.com/docs/6中重新申请一次 --------------------------- <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    private String baseUrl = "http://apis.haoservice.com/";
    private String key = "8d1c763f9d214afa90e9e3cc23d287dd";

    private TextView tvInfo;
    public static final String TAG = "zhk-MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvInfo = findViewById(R.id.tvInfo);

        getWeather();
        getWeatherByRxjava2();
    }

    private void getWeather() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIService service = retrofit.create(APIService.class);
        service.loadWeather("杭州", key).enqueue(new Callback<Weather>() {
            @Override
            public void onResponse(Call<Weather> call, Response<Weather> response) {
                if (response.body() != null) {
                    Weather weather = response.body();
                    printWeatherInfo(weather);
                } else {
                    Log.e(TAG, "onResponse: body==null");
                }
            }

            @Override
            public void onFailure(Call<Weather> call, Throwable t) {
                Log.e(TAG, "onFailure: ", t);
            }
        });
    }

    private void getWeatherByRxjava2() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        APIService service = retrofit.create(APIService.class);
        service.getWeatherData("杭州", key)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<Weather>() {
                    @Override
                    public void accept(Weather weather) throws Exception {
                        String info = printWeatherInfo(weather);
                        tvInfo.setText(info);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.i(TAG, "onError:" + throwable.getMessage());
                        tvInfo.setText(throwable.getMessage());
                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {

                    }
                });
    }

    private String printWeatherInfo(Weather weather) {
        Weather.ResultEntity.TodayEntity todayEntiry = weather.getResult().getToday();
        String info = "城市:" + todayEntiry.getCity() + " 温度:" + todayEntiry.getTemperature();
        Log.i("zhk-MainActivity", "onNext: " + info);
        Log.i(TAG, "onResponse: " + info);
        return info;
    }
}
