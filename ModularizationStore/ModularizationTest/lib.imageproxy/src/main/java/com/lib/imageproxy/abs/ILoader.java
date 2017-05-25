package com.lib.imageproxy.abs;

import android.net.Uri;
import android.support.annotation.IntegerRes;

import java.io.File;

/**
 * @Description 装载者
 * Created by EthanCo on 2016/6/27.
 */
public interface ILoader {
    /**
     * 加载图片
     *
     * @param url 图片URL
     * @return
     */
    public abstract ICreator load(String url);

    public abstract ICreator load(Uri uri);

    public abstract ICreator load(File file);

    public abstract ICreator load(@IntegerRes Integer resourceId);

    public abstract ICreator load(byte[] model);

    public abstract <V> ICreator load(V model);
}
