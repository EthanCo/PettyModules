package com.ethanco.rxjava2test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;


/**
 * RxJava 2.0 最核心的是Publisher和Subscriber。Publisher可以发出一系列的事件，而Subscriber负责和处理这些事件。
 * 平常用得最多的Publisher是Flowabled，支持背压
 *
 * http://blog.csdn.net/qq_35064774/article/details/53057332
 * http://blog.csdn.net/qq_35064774/article/category/6505258
 *
 * 背压的理解
 * http://www.cnblogs.com/iceTing/p/6238207.html
 * http://blog.csdn.net/jdsjlzx/article/details/52717636
 *
 */
public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Z-Main";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //创建Flowable -> 发送一个字符串 "hello RxJava 2"
        Flowable<String> flowable = Flowable.create(new FlowableOnSubscribe<String>() {
            @Override
            public void subscribe(FlowableEmitter<String> e) throws Exception {
                e.onNext("hello RxJava 2");
                e.onComplete();
            }
        }, BackpressureStrategy.BUFFER);

        //创建一个Subscriber
        Subscriber<String> subscriber = new Subscriber<String>() {
            @Override
            public void onSubscribe(Subscription subscription) {
                Log.i(TAG, "onSubscribe");
                //调用request请求资源，参数是请求的数量，一般如果不限制请求数量，可以写Long.MAX_VALUE
                //如果不调用request，Subscriber的onNext和onComplete方法将不会被调用
                subscription.request(Long.MAX_VALUE);
            }

            @Override
            public void onNext(String text) {
                Log.i(TAG, "onNext:" + text);
            }

            @Override
            public void onError(Throwable t) {
                Log.i(TAG, "onError:" + t.getMessage());
            }

            @Override
            public void onComplete() {
                Log.i(TAG, "onComplete");
            }
        };

        //订阅
        flowable.subscribe(subscriber);


        //更简单
        Flowable.just("hello RxJava 2")
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(@NonNull String s) throws Exception {
                        Log.i(TAG, "accept:" + s);
                    }
                });

        //变换
        Flowable.just(123)
                .map(new Function<Integer, String>() {
                    @Override
                    public String apply(@NonNull Integer value) throws Exception {
                        //throw new IllegalArgumentException("xxxx");
                        return String.valueOf(value + 666);
                    }
                })
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(@NonNull String s) throws Exception {
                        Log.i(TAG, "accept:" + s);
                    }
                });

        //如果不实现OnError，在调用过程的抛出的异常不会进行集中捕获(就是说和普通代码一样抛出异常)


    }
}
