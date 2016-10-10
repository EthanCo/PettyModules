package com.nbhope.hopelauncher.lib.network.sbscribe.base;

import rx.Subscriber;

/**
 * Created by EthanCo on 2016/1/3.
 */
public abstract class BaseSubscriber<T> extends Subscriber<T> {
    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onNext(T t) {

    }
}
