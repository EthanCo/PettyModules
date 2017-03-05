package com.ethanco.simpleframe.frame.rxjava.net;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Retrofit工厂类
 * <p>
 * Created by Zhk on 2016/1/5.
 */
public class RetrofitFactory {
    private static Retrofit sinaRetrofit;
    private static Boolean haveBaseUrl = false;

    public static Retrofit createSINARetrofit(String baseUrl) {
        haveBaseUrl = true;
        return getGsonRxJava53iqComponentRetrofit(baseUrl);
    }

    /**
     * 在已调用过createSINARetrofit(String baseUrl)后可使用
     *
     * @return
     */
    public static Retrofit createSINARetrofit() {
        if (!haveBaseUrl) {
            throw new IllegalStateException("在已调用过createSINARetrofit(String baseUrl)后可使用");
        }
        return getGsonRxJava53iqComponentRetrofit(null);
    }

    private static Retrofit getGsonRxJava53iqComponentRetrofit(String baseUrl) {
        if (sinaRetrofit == null) {
            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(new HttpLoggingInterceptor()
                            .setLevel(HttpLoggingInterceptor.Level.BODY))
                    .build();

            sinaRetrofit = new Retrofit.Builder().baseUrl(baseUrl)
                    .addConverterFactory(new ToStringConverterFactory())
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .client(client)
                    .build();
        }
        return sinaRetrofit;
    }
}
