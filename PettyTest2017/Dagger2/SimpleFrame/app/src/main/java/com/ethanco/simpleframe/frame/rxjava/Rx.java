package com.ethanco.simpleframe.frame.rxjava;

import android.util.Log;

import rx.Subscriber;
import rx.functions.Action0;
import rx.functions.Action1;

/**
 * @Description RxJava工具类
 * Created by EthanCo on 2016/3/18.
 */
public class Rx {
    public static final String TAG = "Z-Rxjava";

    /**
     * Subscriber必须要实现onError，否则出现错误会报OnErrorNotImplementedException错误
     * 在subscribe()中使用，自动实现onError log
     *
     * @return
     */
    public static <T> Subscriber sub(final Action1<? super T> onNext) {
        if (onNext == null) {
            throw new IllegalArgumentException("onNext can not be null");
        }

        return new Subscriber<T>() {

            @Override
            public final void onCompleted() {
                // do nothing
            }

            @Override
            public final void onError(Throwable e) {
                Log.e(TAG, "onError: " + e.getMessage());
            }

            @Override
            public final void onNext(T args) {
                onNext.call(args);
            }

        };
    }

    /**
     * Subscriber必须要实现onError，否则出现错误会报OnErrorNotImplementedException错误
     * 在subscribe()中使用，自动实现onError log
     *
     * @return
     */
    public static <T> Subscriber sub(final Action1<? super T> onNext, final Action0 onComplete) {
        if (onNext == null) {
            throw new IllegalArgumentException("onNext can not be null");
        }
        if (onComplete == null) {
            throw new IllegalArgumentException("onComplete can not be null");
        }

        return new Subscriber<T>() {

            @Override
            public final void onCompleted() {
                onComplete.call();
            }

            @Override
            public final void onError(Throwable e) {
                Log.e(TAG, "onError: " + e.getMessage());
            }

            @Override
            public final void onNext(T args) {
                onNext.call(args);
            }

        };
    }
}
