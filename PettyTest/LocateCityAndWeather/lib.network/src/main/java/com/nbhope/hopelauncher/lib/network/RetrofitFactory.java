package com.nbhope.hopelauncher.lib.network;

import android.app.Application;
import android.content.Context;
import android.os.Environment;
import android.support.annotation.NonNull;

import com.nbhope.hopelauncher.lib.network.observer.Observer;
import com.nbhope.hopelauncher.lib.network.persistentcookiejar.ClearableCookieJar;
import com.nbhope.hopelauncher.lib.network.persistentcookiejar.PersistentCookieJar;
import com.nbhope.hopelauncher.lib.network.persistentcookiejar.cache.SetCookieCache;
import com.nbhope.hopelauncher.lib.network.persistentcookiejar.persistence.SharedPrefsCookiePersistor;

import java.io.File;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
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
class RetrofitFactory {
    private OkHttpClient okHttpClient;
    private static final long HTTP_RESPONSE_DISK_CACHE_MAX_SIZE = 20 * 1024 * 1024;

    private RetrofitFactory() {
        initBodyInterceptor();
    }

    public static RetrofitFactory getInstance() {
        return SingletonHodler.sInstance;
    }

    private static class SingletonHodler {
        private static final RetrofitFactory sInstance = new RetrofitFactory();
    }

    private static final int TIME_OUT = 15;
    private HashMap<String, Retrofit> retrofitMap = new HashMap<>();

    public Retrofit createRetrofit(String baseUrl) {
        if (mContext == null) {
            throw new IllegalStateException("context is null,please use NetFacade.init() first");
        }

        Retrofit retrofit = retrofitMap.get(baseUrl);

        if (retrofit == null) {
            OkHttpClient client = createOkhttp();

            retrofit = new Retrofit.Builder().baseUrl(baseUrl)
                    .addConverterFactory(new ToStringConverterFactory())
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .client(client)
                    .build();

            retrofitMap.put(baseUrl, retrofit);
        }

        return retrofit;
    }

    @NonNull
    public OkHttpClient createOkhttp() {
        if (null == okHttpClient) {
            File httpCacheDirectory = new File(getCacheDir(getContext()), "Http_Cache");
            CookieManager cookieManager = new CookieManager();
            cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ALL);
            CookieHandler.setDefault(cookieManager);
            ClearableCookieJar jncj = new PersistentCookieJar(
                    new SetCookieCache(), new SharedPrefsCookiePersistor(getContext()));
            //JavaNetCookieJar jncj = new JavaNetCookieJar(CookieHandler.getDefault());
            okHttpClient = new OkHttpClient.Builder()
                    .addInterceptor(new HttpLoggingInterceptor()
                            .setLevel(HttpLoggingInterceptor.Level.BODY))
                    .addInterceptor(bodyInterceptor)
                    .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
                    .readTimeout(TIME_OUT, TimeUnit.SECONDS)
                    .writeTimeout(TIME_OUT, TimeUnit.SECONDS)
                    .retryOnConnectionFailure(true) //错误重连
                    .cache(new Cache(httpCacheDirectory,  //缓存
                            HTTP_RESPONSE_DISK_CACHE_MAX_SIZE))
                    .cookieJar(jncj)
                    .build();
        }

        return okHttpClient;
    }

    private static BodyInterceptor bodyInterceptor;

    /**
     * 注册 响应观察者
     *
     * @param observer
     */
    public static void register(Observer observer) {
        initBodyInterceptor();
        bodyInterceptor.register(observer);
    }

    private static void initBodyInterceptor() {
        if (null == bodyInterceptor) {
            bodyInterceptor = new BodyInterceptor();
        }
    }

    public static Context getContext() {
        return mContext;
    }

    private static Application mContext;

    public static void init(Application application) {
        mContext = application;
    }

    public String getCacheDir(Context context) {
        String path;
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            if (null == context.getExternalCacheDir()) {
                path = Environment.getExternalStorageDirectory().getPath();
            } else {
                path = context.getExternalCacheDir().getPath();
            }
        } else {
            path = context.getCacheDir().getPath();
        }
        return path;
    }

}
