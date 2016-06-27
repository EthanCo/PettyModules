package cn.nbhope.imageproxylib.abs;

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
    public abstract ILoador with(android.app.Fragment fragment);

    /**
     * 初始化Proxy
     *
     * @param fragment
     * @return
     */
    public abstract ILoador with(Fragment fragment);

    /**
     * 初始化Proxy
     *
     * @param activity
     * @return
     */
    public abstract ILoador with(Activity activity);

//    /**
//     * 加载图片
//     *
//     * @param url 图片URL
//     * @return
//     */
//    public abstract ICreator load(String url);
//
//    public abstract ICreator load(Uri uri);
//
//    public abstract ICreator load(File file);
//
//    public abstract ICreator load(@IntegerRes Integer resourceId);
//
//    public abstract ICreator load(byte[] model);
//
//    public abstract <V> ICreator load(V model);

    /**
     * 初始化Proxy
     *
     * @param context
     * @return
     */
    public abstract ILoador with(Context context);
}
