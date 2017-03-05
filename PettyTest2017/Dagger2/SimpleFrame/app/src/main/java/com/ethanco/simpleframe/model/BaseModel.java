package com.ethanco.simpleframe.model;

import com.ethanco.simpleframe.MyApplication;
import com.ethanco.simpleframe.frame.utils.ACache;
import com.google.gson.Gson;

/**
 * @Description TODO
 * Created by EthanCo on 2016/4/6.
 */
public abstract class BaseModel {
    protected final MyApplication application;
    protected final ACache aCache;
    protected final Gson gson;

    public BaseModel() {
        this.application = MyApplication.getInstance();
        this.aCache = ACache.get(application);
        this.gson = new Gson();
    }
}
