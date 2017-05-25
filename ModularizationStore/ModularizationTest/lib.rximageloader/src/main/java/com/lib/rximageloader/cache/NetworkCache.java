package com.lib.rximageloader.cache;

import android.graphics.Bitmap;

import rx.Observable;

/**
 * 作者：kelingqiu on 17/1/4 11:30
 * 邮箱：42747487@qq.com
 */

public interface NetworkCache {
    public abstract Observable<Bitmap> get(String key);
}
