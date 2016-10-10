package com.nbhope.hopelauncher.lib.network.sbscribe;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;

/**
 * @Description 策略执行者
 * Created by EthanCo on 2016/8/15.
 */
class StrategyExecutor<T> {
    private static final String TAG = "Z-StrategyExecutor";
    private List<Subscriber<T>> subscribers = new ArrayList<>();

    public void add(Subscriber<T> subscriber) {
        subscribers.add(subscriber);
    }

    public void execErrors(Throwable e) {
        for (Subscriber<T> subscriber : subscribers) {
            subscriber.onError(e);
        }
    }

    public void execCompleteds() {
        for (Subscriber<T> subscriber : subscribers) {
            subscriber.onCompleted();
        }
    }

    public void execNexts(T t) {
        for (Subscriber<T> subscriber : subscribers) {
            subscriber.onNext(t);
        }
    }
}
