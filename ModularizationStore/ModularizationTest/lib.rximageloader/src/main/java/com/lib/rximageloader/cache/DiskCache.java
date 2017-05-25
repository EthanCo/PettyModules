package com.lib.rximageloader.cache;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.lib.rximageloader.CacheLoader;
import com.lib.rximageloader.abs.ICache;
import com.lib.rximageloader.utils.NetWorkUtil;

import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 作者：kelingqiu on 17/1/4 11:03
 * 邮箱：42747487@qq.com
 */

public class DiskCache implements ICache {
    private static final String TAG = "DiskCache";
    public static long OTHER_CACHE_TIME = 10*60*1000;
    public static long WIFI_CACHE_TIME = 30*60*1000;
    private static final boolean needCacheFailure = false;
    File fileDir;
    public DiskCache(){
        fileDir = CacheLoader.getApplication().getCacheDir();
    }

    @Override
    public Observable<Bitmap> get(final String key) {
        return Observable.create(new Observable.OnSubscribe<Bitmap>() {
            @Override
            public void call(Subscriber<? super Bitmap> subscriber) {

                if (subscriber.isUnsubscribed()){
                    return;
                }

                Bitmap bitmap = getDiskData(key);
                subscriber.onNext(bitmap);

                subscriber.onCompleted();
            }
        });
    }

    @Override
    public void put(final String key, final Bitmap bitmap) {
        Observable.create(new Observable.OnSubscribe<Bitmap>() {
            @Override
            public void call(Subscriber<? super Bitmap> subscriber) {
                boolean isSuccess = isSave(key,bitmap);
                if (!subscriber.isUnsubscribed() && isSuccess){
                    subscriber.onNext(bitmap);
                    subscriber.onCompleted();
                }
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();

    }

    private boolean isSave(String fileName,Bitmap bitmap){
        File file = new File(fileDir,formatFileName(fileName));

        OutputStream out = null;
        boolean isSuccess = false;
        try {
            out = new FileOutputStream(file);
            Bitmap.CompressFormat format;
            format = Bitmap.CompressFormat.JPEG;
            bitmap.compress(format,100,out);
            out.flush();
            isSuccess = true;
        } catch (FileNotFoundException e) {
            Log.e(TAG,e.getMessage());
        } catch (IOException e) {
            Log.e(TAG,e.getMessage());
        }finally {
            closeSilently(out);
        }
        return isSuccess;
    }

    /**
     * 判断缓存是否已经失效
     */
    private boolean isCacheDataFailure(File dataFile) {
        if (!dataFile.exists()) {
            return false;
        }
        long existTime = System.currentTimeMillis() - dataFile.lastModified();
        boolean failure = false;
        if (NetWorkUtil.isWifiByType(CacheLoader.getApplication())) {
            failure = existTime > WIFI_CACHE_TIME ? true : false;
        } else {
            failure = existTime > OTHER_CACHE_TIME ? true : false;
        }

        return failure;
    }

    private Bitmap getDiskData(String fileName){
        File file = new File(fileDir,formatFileName(fileName));

        if (needCacheFailure && isCacheDataFailure(file)){
            return null;
        }

        if (!file.exists()){
            return null;
        }
        Bitmap bitmap = null;
        try{
            bitmap = BitmapFactory.decodeStream(new FileInputStream(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    private String formatFileName(String url){
        String temp = url.replaceAll(File.separator,"-");
        return temp;
    }

    private void closeSilently(Closeable closeable){
        if (closeable != null){
            try {
                closeable.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void clearDisk(String key){
        File file = new File(fileDir,key);
        if (file.exists()) file.delete();
    }
}
