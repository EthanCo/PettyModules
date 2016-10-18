package com.nbhope.hopelauncher.lib.network.sbscribe;

import android.util.Log;

import com.nbhope.hopelauncher.lib.network.sbscribe.anno.LoadFailed;
import com.nbhope.hopelauncher.lib.network.sbscribe.matcher.ActionMatcher;
import com.nbhope.hopelauncher.lib.network.sbscribe.matcher.LoadFailedMatcher;
import com.nbhope.hopelauncher.lib.network.sbscribe.matcher.ProcessDialogMatcher;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description 策略生成者
 * Created by EthanCo on 2016/8/12.
 */
class StrategyMaker<T> {
    private static final String TAG = "Z-StrategyMarker";
    private List<StrategyMacther> matchers = new ArrayList<>();

    StrategyMaker(StrategyMacther.MatchListener<T> matchListener) {
        //init matchers
        matchers.add(new ProcessDialogMatcher(matchListener));
        matchers.add(new LoadFailedMatcher(matchListener));
        matchers.add(new ActionMatcher(matchListener));
    }

    /**
     * @param o
     * @param iterate 是否轮询其父接口获取更多的 StrategyMacther
     */
    void recordAction(Object o, boolean iterate) {
        if (o == null) return;
        if (iterate) {
            iterateClasses(o, o.getClass(), LoadFailed.DEFAULT_VALUE);
        } else {
            handleIfMatching(o, o.getClass(), LoadFailed.DEFAULT_VALUE);
        }
    }

    /**
     * @param o
     * @param iterate 是否轮询其父接口获取更多的 StrategyMacther
     * @param flag    标记
     */
    void recordAction(Object o, boolean iterate, int flag) {
        if (o == null) return;
        if (iterate) {
            iterateClasses(o, o.getClass(), flag);
        } else {
            handleIfMatching(o, o.getClass(), flag);
        }
    }

    private void iterateClasses(Object o, Class cls, int flag) {
        handleIfMatching(o, cls, flag);

        Class superClass = cls.getSuperclass();
        if (superClass != null) handleIfMatching(o, superClass, flag);

        Class[] interfaceClasses = cls.getInterfaces();
        if (interfaceClasses == null) return;

        for (Class interfaceCls : interfaceClasses) {
            iterateClasses(o, interfaceCls, flag);
        }
    }

    private void handleIfMatching(Object o, Class cls, int flag) {
        String className = cls.getName();
        Log.i(TAG, "ClassName:" + className);
        for (StrategyMacther matcher : matchers) {
            matcher.handle(o, cls, flag);
        }
    }
}
