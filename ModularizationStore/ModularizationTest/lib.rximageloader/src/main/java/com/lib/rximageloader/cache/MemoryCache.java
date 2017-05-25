package com.lib.rximageloader.cache;

import android.graphics.Bitmap;
import android.util.LruCache;

import com.lib.rximageloader.abs.ICache;

import rx.Observable;
import rx.Subscriber;

/**
 * 作者：kelingqiu on 17/1/4 10:52
 * 邮箱：42747487@qq.com
 * 内存存储
 */

public class MemoryCache implements ICache{
    private LruCache<String,Bitmap> mCache;

    public MemoryCache(){
        final int maxMemory = (int) Runtime.getRuntime().maxMemory();
        final int cacheSize = maxMemory/8;
        mCache = new LruCache<String,Bitmap>(cacheSize){
            @Override
            protected int sizeOf(String key, Bitmap value) {
                int size= 0 ;
                if (value != null){
                    size = value.getRowBytes() * value.getHeight();
                }
                return size;
            }
        };
    }




    @Override
    public Observable<Bitmap> get(final String key) {
        return Observable.create(new Observable.OnSubscribe<Bitmap>(){

            @Override
            public void call(Subscriber<? super Bitmap> subscriber) {
                Bitmap result = mCache.get(key);

                if (subscriber.isUnsubscribed()){
                    return;
                }
                subscriber.onNext(result);
                subscriber.onCompleted();
            }
        });
    }

    @Override
    public void put(String key, Bitmap bitmap) {
        if (null != bitmap){
            mCache.put(key,bitmap);
        }
    }

    public void clearMemory(String key){
        mCache.remove(key);
    }
}
