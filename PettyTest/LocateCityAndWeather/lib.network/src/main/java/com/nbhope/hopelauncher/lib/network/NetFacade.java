package com.nbhope.hopelauncher.lib.network;

import android.app.Application;
import android.os.Build;
import android.util.ArrayMap;

import com.nbhope.hopelauncher.lib.network.observer.Observer;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Retrofit;

/**
 * @Description 网络访问 门面
 * 网络访问通过该类进行
 * Created by EthanCo on 2016/8/8.
 */
public class NetFacade {
    private static final String BASE_URL = "http://121.40.227.8:8088/";
    private Map<String, APIService> serviceMap;

    private NetFacade() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            serviceMap = new ArrayMap<>();
        } else {
            serviceMap = new HashMap<>();
        }
    }

    public static NetFacade getInstance() {
        return SingleTonHolder.instance;
    }

    private static class SingleTonHolder {
        private static NetFacade instance = new NetFacade();
    }

    private Retrofit provideRetrofit(String baseUrl) {
        return RetrofitFactory.getInstance().createRetrofit(baseUrl);
    }

    public <T> T provideService(String baseUrl, Class clz) {
        T service = (T) serviceMap.get(baseUrl);
        if (service == null) {
            service = (T) provideRetrofit(baseUrl).create(clz);
        }
        return service;
    }

    public APIService provideDefualtService() {
        APIService service = serviceMap.get(BASE_URL);
        if (service == null) {
            service = provideRetrofit(BASE_URL).create(APIService.class);
        }
        return service;
    }

    /**
     * 初始化
     *
     * @param application
     */
    public static void init(Application application) {
        if (RetrofitFactory.getContext() == null) {
            RetrofitFactory.init(application);
        }
    }

    /**
     * 注册 响应观察者
     *
     * @param observer
     */
    public static void register(Observer observer) {
        RetrofitFactory.register(observer);
    }
}