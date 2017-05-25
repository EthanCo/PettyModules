package com.lib.rximageloader.abs;

import android.graphics.Bitmap;

import rx.Observable;

/**
 * 作者：kelingqiu on 17/1/4 10:50
 * 邮箱：42747487@qq.com
 */

public interface ICache {
    Observable<Bitmap> get(String key);

    void put(String ket,Bitmap bitmap);
}
