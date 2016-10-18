package com.nbhope.hopelauncher.lib.network.sbscribe.matcher;

import com.nbhope.hopelauncher.lib.network.sbscribe.StrategyMacther;
import com.nbhope.hopelauncher.lib.network.sbscribe.base.BaseSubscriber;
import com.nbhope.hopelauncher.lib.network.sbscribe.matcher.bean.ActionBean;

import rx.Subscriber;

/**
 * @Description 出入ActionBean的匹配
 * Created by EthanCo on 2016/8/15.
 */
public class ActionMatcher<T> extends StrategyMacther<T> {
    public ActionMatcher(MatchListener<T> matchListener) {
        super(matchListener);
    }

    @Override
    protected Subscriber<T> generateSubscriber(Object o, Class cls, int flag) {
        final ActionBean actionBean = (ActionBean) o;

        return new BaseSubscriber<T>() {
            @Override
            public void onNext(T t) {
                if (actionBean.getOnNext() != null) {
                    actionBean.getOnNext().call(t);
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                if (actionBean.getOnError() != null) {
                    actionBean.getOnError().call(e);
                }
            }

            @Override
            public void onCompleted() {
                super.onCompleted();
                if (actionBean.getOnCompleted() != null) {
                    actionBean.getOnCompleted().call();
                }
            }
        };
    }

    @Override
    public boolean matching(Object o, String className) {
        if (o instanceof ActionBean) {
            return true;
        }
        return false;
    }
}
