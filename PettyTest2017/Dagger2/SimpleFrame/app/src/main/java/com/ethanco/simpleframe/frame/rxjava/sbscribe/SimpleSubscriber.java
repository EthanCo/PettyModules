package com.ethanco.simpleframe.frame.rxjava.sbscribe;


import com.ethanco.simpleframe.frame.utils.L;

import rx.functions.Action1;

/**
 * Created by Zhk on 2016/1/3.
 */
public class SimpleSubscriber<T> extends BaseSubscriber<T> {

    private final Action1<? super T> onNext;

    public SimpleSubscriber(final Action1<? super T> onNext) {
        if (onNext == null) {
            throw new IllegalArgumentException("onNext can not be null");
        }

        this.onNext = onNext;
    }

    @Override
    public void onCompleted() {
        //do nothing
    }

    @Override
    public void onError(Throwable e) {
        L.i("SimpleSubscriber", "onError: " + e.getMessage());
    }

    @Override
    public void onNext(T t) {
        onNext.call(t);
    }
}
