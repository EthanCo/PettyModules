package com.nbhope.hopelauncher.lib.network.sbscribe;


import com.nbhope.hopelauncher.lib.network.sbscribe.base.LogSubscriber;
import com.nbhope.hopelauncher.lib.network.sbscribe.matcher.bean.ActionBean;

import rx.Subscriber;
import rx.functions.Action0;
import rx.functions.Action1;

/**
 * 一般情况下使用该Subscriber即可
 * 尽量使用该类及其子类，方面日后进行统一处理
 * Created by EthanCo on 2016/8/10.
 */
public class RxSubscriber<T> extends LogSubscriber<T> {

    private StrategyExecutor<T> executor;
    private StrategyMaker<T> maker;

    public RxSubscriber() {
        executor = new StrategyExecutor<>();
        maker = new StrategyMaker<>(matchListener);
    }

    StrategyMacther.MatchListener matchListener = new StrategyMacther.MatchListener() {
        @Override
        public void matchSuccess(Subscriber subscriber) {
            executor.add(subscriber);
        }
    };

    /**
     * @param onNext 对 OnNext 进行自定义处理
     */
    public RxSubscriber(final Action1<? super T> onNext) {
        this();
        if (onNext == null) {
            throw new IllegalArgumentException("onNext can not be null");
        }

        maker.recordAction(new ActionBean<T>(onNext), false);
    }

    /**
     * @param onCompleted 对onCompeled进行自定义处理
     */
    public RxSubscriber(final Action0 onCompleted) {
        this();
        if (onCompleted == null) {
            throw new IllegalArgumentException("onCompleted can not be null");
        }

        maker.recordAction(new ActionBean<T>(onCompleted), false);
    }

    /**
     * @param onNext 对 OnNext 进行自定义处理
     * @param o      传入ProcessDialogView子类 自动调用 dismissProcessDialog，出现错误时自动调用有@LoadFailed注解的方法
     * @param flag   标记，用作识别，传入该值后，注解的值需和该标记的值相同才会起作用
     */
    public RxSubscriber(final Action1<? super T> onNext, Object o, int flag) {
        this(onNext);
        maker.recordAction(o, true, flag);
    }

    /**
     * @param onCompleted 对onCompeled进行自定义处理
     * @param o           传入ProcessDialogView子类 自动调用 dismissProcessDialog，出现错误时自动调用有@LoadFailed注解的方法
     * @param flag        标记，用作识别，传入该值后，注解的值需和该标记的值相同才会起作用
     */
    public RxSubscriber(Action0 onCompleted, Object o, int flag) {
        this(onCompleted);
        maker.recordAction(o, true, flag);
    }

    /**
     * @param o    传入ProcessDialogView子类 自动调用 dismissProcessDialog，出现错误时自动调用有@LoadFailed注解的方法
     * @param flag 标记，用作识别，传入该值后，注解的值需和该标记的值相同才会起作用
     */
    public RxSubscriber(Object o, int flag) {
        this();
        maker.recordAction(o, true, flag);
    }

    /**
     * @param onNext 对 OnNext 进行自定义处理
     * @param o      传入ProcessDialogView子类 自动调用 dismissProcessDialog，出现错误时自动调用有@LoadFailed注解的方法
     */
    public RxSubscriber(final Action1<? super T> onNext, Object o) {
        this(onNext);
        maker.recordAction(o, true);
    }

    /**
     * @param onCompleted 对onCompeled进行自定义处理
     * @param o           传入ProcessDialogView子类 自动调用 dismissProcessDialog，出现错误时自动调用有@LoadFailed注解的方法
     */
    public RxSubscriber(Action0 onCompleted, Object o) {
        this(onCompleted);
        maker.recordAction(o, true);
    }

    /**
     * @param o 传入ProcessDialogView子类 自动调用 dismissProcessDialog，出现错误时自动调用有@LoadFailed注解的方法
     */
    public RxSubscriber(Object o) {
        this();
        maker.recordAction(o, true);
    }


    @Override
    public void onCompleted() {
        super.onCompleted();
        executor.execCompleteds();
    }

    @Override
    public void onNext(T t) {
        super.onNext(t);
        executor.execNexts(t);
    }

    @Override
    public void onError(Throwable e) {
        super.onError(e);
        executor.execErrors(e);
    }

    //============================= Z-使用示例 ==============================/

    /*
    //不使用回调，只需打印log的情况
    public void sample1() {
        Observable.just("123")
                .subscribe(new RxSubscriber<String>());
    }

    //只使用 onNext 的情况
    public void sample2() {
        Observable.just("123")
                .subscribe(new RxSubscriber<String>(new Action1<String>() {
                    @Override
                    public void call(String s) {

                    }
                }));
    }

    //只使用 onCompleted 的情况
    public void sample3() {
        Observable.just("123")
                .subscribe(new RxSubscriber<String>(new Action0() {
                    @Override
                    public void call() {

                    }
                }));
    }

    //使用多个回调的情况
    public void sample4() {
        Observable.just("123")
                .subscribe(new RxSubscriber<String>(new Action1<String>() {
                    @Override
                    public void call(String s) {

                    }
                }) {
                    //以重载的形式

                    @Override
                    public void onCompleted() {
                        super.onCompleted();
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                    }
                });
    }*/

    //传入getView() 自动调用 dismissProcessDialog (需继承ProcessDialogView)，出现错误时自动调用有@LoadFailed注解的方法
    /*Observable.just(null)
            .map(new Func1<Object, CmdRequest>() {
        @Override
        public CmdRequest call(Object o) {
            return RequestModel.getInstance().generationGetServiceTimeCmd();
        }
    })
            .flatMap(new Func1<CmdRequest, Observable<TimeResponse>>() {
        @Override
        public Observable<TimeResponse> call(CmdRequest cmd) {
            return getServiceTimeFromNet(cmd);
        }
    })
            .compose(RxHelper.<TimeResponse.Entity>handleResult()) //检查返回结果是否成功，并切换线程
            .subscribe(new RxSubscriber(new Action1<TimeResponse.Entity>() {
        @Override
        public void call(TimeResponse.Entity entity) {
            getView().getServiceTimeSuccess(entity.getTime());
        }
    }, getView()));*/
}
