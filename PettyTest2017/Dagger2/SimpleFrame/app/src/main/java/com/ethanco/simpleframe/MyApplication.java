package com.ethanco.simpleframe;


import android.util.Log;

import com.ethanco.simpleframe.dagger.ApplicationComponent;
import com.ethanco.simpleframe.dagger.DaggerApplicationComponent;
import com.ethanco.simpleframe.frame.BaseApplication;

import javax.inject.Inject;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

/**
 * @Description TODO
 * Created by EthanCo on 2016/3/30.
 */
public class MyApplication extends BaseApplication {
    @Inject
    public OkHttpClient client;

    @Inject
    public Retrofit retrofit;

    private static MyApplication instance;

    public static MyApplication getInstance() {
        return instance;
    }

    private static final String BUGLY_APPID = "900024404";

    public ApplicationComponent getAppConponent() {
        return appConponent;
    }

    public void setAppConponent(ApplicationComponent appConponent) {
        this.appConponent = appConponent;
    }

    private ApplicationComponent appConponent;

    @Override
    protected String getBuglyAppID() {
        return BUGLY_APPID;
    }

    @Override
    protected boolean isDebug() {
        return BuildConfig.DEBUG;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        appConponent = DaggerApplicationComponent.builder().build();
        MyApplication.getInstance().getAppConponent().inject(this);
        Log.i("Z-LoginActivity", "initVar client!=null: " + (client != null));
        Log.i("Z-LoginActivity", "initVar retrofit!=null: " + (retrofit != null));
    }
}
