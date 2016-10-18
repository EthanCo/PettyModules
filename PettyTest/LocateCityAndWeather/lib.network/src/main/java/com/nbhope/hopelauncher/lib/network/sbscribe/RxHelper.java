package com.nbhope.hopelauncher.lib.network.sbscribe;

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
