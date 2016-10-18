package com.nbhope.hopelauncher.lib.network.sbscribe;

import rx.Subscriber;

/**
 * @Description 策略匹配器
 * Created by EthanCo on 2016/8/15.
 */
public abstract class StrategyMacther<T> {
    public boolean handle(final Object o, Class cls, int flag) {
        String className = cls.getName();
        if (!matching(o, className)) return false;

        Subscriber<T> subscriber = generateSubscriber(o, cls, flag);
        if (subscriber != null) {
            if (matchListener != null) {
                matchListener.matchSuccess(subscriber);
            }
            return true;
        } else {
            return false;
        }
    }

    protected abstract Subscriber<T> generateSubscriber(final Object o, Class cls, int flag);

    public abstract boolean matching(Object o, String className);

    protected MatchListener<T> matchListener;

    public StrategyMacther(MatchListener<T> matchListener) {
        this.matchListener = matchListener;
    }

    public interface MatchListener<T> {
        void matchSuccess(Subscriber<T> subscriber);
    }
}
