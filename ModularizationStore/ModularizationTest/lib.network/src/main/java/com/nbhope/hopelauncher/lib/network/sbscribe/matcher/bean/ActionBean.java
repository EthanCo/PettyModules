package com.nbhope.hopelauncher.lib.network.sbscribe.matcher.bean;

import rx.functions.Action0;
import rx.functions.Action1;

/**
 * Created by EthanCo on 2016/8/15.
 */
public class ActionBean<T> {
    private Action1<? super T> onNext;
    private Action1<? super T> onError;
    private Action0 onCompleted;

    public ActionBean() {
    }

    public ActionBean(Action1<? super T> onNext) {
        this.onNext = onNext;
    }

    public ActionBean(Action0 onCompleted) {
        this.onCompleted = onCompleted;
    }

    public ActionBean(Action1<? super T> onNext, Action1<? super T> onError, Action0 onCompleted) {
        this.onNext = onNext;
        this.onError = onError;
        this.onCompleted = onCompleted;
    }

    public Action0 getOnCompleted() {
        return onCompleted;
    }

    public void setOnCompleted(Action0 onCompleted) {
        this.onCompleted = onCompleted;
    }

    public Action1<? super T> getOnNext() {
        return onNext;
    }

    public void setOnNext(Action1<? super T> onNext) {
        this.onNext = onNext;
    }

    public Action1<? super T> getOnError() {
        return onError;
    }

    public void setOnError(Action1<? super T> onError) {
        this.onError = onError;
    }
}
