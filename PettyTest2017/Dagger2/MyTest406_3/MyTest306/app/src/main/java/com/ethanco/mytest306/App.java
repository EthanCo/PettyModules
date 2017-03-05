package com.ethanco.mytest306;

import android.app.Application;

/**
 * Created by EthanCo on 2016/4/6.
 */
public class App extends Application {

    private static App instance;

    public ModelComponent getModelComponent() {
        return modelComponent;
    }

    private ModelComponent modelComponent;

    public DaggerAppComponent.Builder getBuilder() {
        return builder;
    }

    private DaggerAppComponent.Builder builder;

    public static App getInstance() {
        return instance;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        builder = DaggerAppComponent.builder();
        modelComponent = DaggerModelComponent.builder().build();
    }
}
