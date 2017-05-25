package com.lib.imageproxy.abs;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;

/**
 * @Description 图片加载总代理 - 抽象类
 * Created by EthanCo on 2016/6/23.
 */
public abstract class ImageProxy {

    /**
     * 初始化Proxy
     *
     * @param fragment
     * @return
     */
    public abstract ILoader with(android.app.Fragment fragment);

    /**
     * 初始化Proxy
     *
     * @param fragment
     * @return
     */
    public abstract ILoader with(Fragment fragment);

    /**
     * 初始化Proxy
     *
     * @param activity
     * @return
     */
    public abstract ILoader with(Activity activity);

    /**
     * 初始化Proxy
     *
     * @param context
     * @return
     */
    public abstract ILoader with(Context context);
}
