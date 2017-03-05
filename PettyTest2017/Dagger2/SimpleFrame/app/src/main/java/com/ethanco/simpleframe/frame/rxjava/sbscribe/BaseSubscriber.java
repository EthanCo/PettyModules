package com.ethanco.simpleframe.frame.rxjava.sbscribe;


import com.ethanco.simpleframe.frame.utils.L;

import rx.Subscriber;

/**
 * Created by Zhk on 2016/1/3.
 */
public abstract class BaseSubscriber<T> extends Subscriber<T> {
    @Override
    public void onCompleted() {
        //do nothing
    }

    @Override
    public void onError(Throwable e) {
        L.i("SimpleSubscriber", "onError: " + e.getMessage());
    }
}
