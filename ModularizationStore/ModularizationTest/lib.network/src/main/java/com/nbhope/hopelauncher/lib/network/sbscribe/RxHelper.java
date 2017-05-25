package com.nbhope.hopelauncher.lib.network.sbscribe;

import android.text.TextUtils;

import com.nbhope.hopelauncher.lib.network.bean.response.BaseDataBean;
import com.nbhope.hopelauncher.lib.network.bean.response.BaseResponse;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * @Description RxJava 帮助类
 * Created by EthanCo on 2016/8/12.
 */
public class RxHelper {
    /**
     * 处理返回结果，如果错误则会执行onError
     * 在网络加载失败时进行重新尝试连接
     * 并且进行线程的切换 subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
     *
     * @param <T>
     * @return
     */
    public static <T extends BaseDataBean> Observable.Transformer<BaseResponse<T>, T> handleResult() {
        return new Observable.Transformer<BaseResponse<T>, T>() {

            @Override
            public Observable<T> call(Observable<BaseResponse<T>> tObservable) {
                return tObservable.flatMap(new Func1<BaseResponse<T>, Observable<T>>() {
                    @Override
                    public Observable<T> call(BaseResponse<T> result) {
                        if (result.getResult()) {
                            return createData(result.getData());
                        } else {
                            return Observable.error(new RuntimeException(result.getData().getMessage()));
                        }
                    }
                }).retryWhen(new RetryWhenNetworkException()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    /**
     * 进行线程的切换 subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
     *
     * @param <T>
     * @return
     */
    public static <T> Observable.Transformer<T, T> transThread() {
        return new Observable.Transformer<T, T>() {
            @Override
            public Observable<T> call(Observable<T> result) {
                //retryWhen(new RetryWhenNetworkException()).
                return result.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    /**
     * 检查是否未空
     *
     * @param object
     * @return 如果为空抛出异常，不为空返回true
     */
    public static boolean checkEmpty(Object object) {
        if (object != null) {
            return true;
        } else {
            throw new IllegalStateException("传入值为空");
        }
    }

    /**
     * 检查是否未空
     *
     * @param text
     * @return 如果为空抛出异常，不为空返回true
     */
    public static boolean checkEmpty(CharSequence text) {
        if (!TextUtils.isEmpty(text)) {
            return true;
        } else {
            throw new IllegalStateException("传入值为空");
        }
    }

    /**
     * 检查是否未空
     *
     * @param object
     * @return 如果为空retrun false，不为空返回true
     */
    public static boolean checkEmptyQuietly(Object object) {
        if (object != null) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 检查是否未空
     *
     * @param text
     * @return 如果为空retrun false，不为空返回true
     */
    public static boolean checkEmptyQuietly(CharSequence text) {
        if (!TextUtils.isEmpty(text)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 比较
     *
     * @param expected 预期值
     * @param actual   实际值
     * @return 如果相等返回true，否则爆出异常
     */
    public static boolean assertEquals(Object expected, Object actual) {
        checkEmpty(expected);
        checkEmpty(actual);

        if (expected == expected || expected.equals(actual)) {
            return true;
        } else {
            throw new IllegalStateException("预期值与实际值不相等。 expected:" + expected + " actual:" + actual);
        }
    }

    /**
     * 比较 不抛出异常
     *
     * @param expected 预期值
     * @param actual   实际值
     * @return 如果相等返回true，否则返回false
     */
    public static boolean assertEqualsQuietly(Object expected, Object actual) {
        checkEmpty(expected);
        checkEmpty(actual);

        if (expected == expected || expected.equals(actual)) {
            return true;
        } else {
            return false;
        }
    }

    private static <T> Observable<T> createData(final T data) {
        return Observable.create(new Observable.OnSubscribe<T>() {
            @Override
            public void call(Subscriber<? super T> subscriber) {
                try {
                    subscriber.onNext(data);
                    subscriber.onCompleted();
                } catch (Exception e) {
                    subscriber.onError(e);
                }
            }
        });
    }
}
