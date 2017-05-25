package com.lib.rximageloader;

import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;

import com.lib.rximageloader.abs.ICache;
import com.lib.rximageloader.cache.DiskCache;
import com.lib.rximageloader.cache.MemoryCache;
import com.lib.rximageloader.cache.NetworkCache;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;


/**
 * 作者：kelingqiu on 17/1/4 11:06
 * 邮箱：42747487@qq.com
 */

public class CacheLoader {
    private static Application application;
    private static final String TAG = "CacheLoader";
    private static final boolean isPrintLog = false;

    public static Application getApplication(){
        if (application == null){
            throw new IllegalStateException("CacheLoader not Instance");
        }
        return application;
    }

    private ICache mMemoryCache,mDiskCache;

    private CacheLoader(){
        mMemoryCache = new MemoryCache();
        mDiskCache = new DiskCache();
    }

    private static CacheLoader loader;

    public static CacheLoader getInstance(Context context){
        application = (Application) context.getApplicationContext();
        if (loader == null){
            loader = new CacheLoader();
        }
        return loader;
    }

    public Observable<Bitmap> asDataObservable(String key,NetworkCache networkCache){
        Observable observable = Observable.concat(
                memory(key),
                disk(key),
                network(key,networkCache))
                .first(new Func1<Bitmap, Boolean>() {
                    @Override
                    public Boolean call(Bitmap bitmap) {
                        return bitmap != null;
                    }
                });
        return observable;
    }

    private Observable<Bitmap> memory(String key){
        return mMemoryCache.get(key).doOnNext(new Action1<Bitmap>() {
            @Override
            public void call(Bitmap bitmap) {
                if (null != bitmap){
                    if (isPrintLog) Log.d(TAG,"内存存储");
                }
            }
        });
    }

    private Observable<Bitmap> disk(final String key){
        return mDiskCache.get(key)
                .doOnNext(new Action1<Bitmap>() {
                    @Override
                    public void call(Bitmap bitmap) {
                        if (null != bitmap){
                            if (isPrintLog) Log.d(TAG,"磁盘存储");
                            mMemoryCache.put(key,bitmap);
                        }
                    }
                });
    }

    private Observable<Bitmap> network(final String key,NetworkCache networkCache){
        return networkCache.get(key)
                .doOnNext(new Action1<Bitmap>() {
                    @Override
                    public void call(Bitmap bitmap) {
                        if (null != bitmap){
                            if (isPrintLog) Log.d(TAG,"网络存储");
                            mMemoryCache.put(key,bitmap);
                            mDiskCache.put(key,bitmap);
                        }
                    }
                });
    }

    public void cleanMemory(String key){
        ((MemoryCache)mMemoryCache).clearMemory(key);
    }

    public void cleanMemoryDisk(String key){
        ((MemoryCache)mMemoryCache).clearMemory(key);
        ((DiskCache)mDiskCache).clearDisk(key);
    }
}
