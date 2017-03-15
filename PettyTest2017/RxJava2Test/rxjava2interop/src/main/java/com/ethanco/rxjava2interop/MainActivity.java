package com.ethanco.rxjava2interop;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import java.util.concurrent.TimeUnit;

import hu.akarnokd.rxjava.interop.RxJavaInterop;
import io.reactivex.Flowable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import rx.Observable;
import rx.functions.Action1;

/**
 * RxJava和RxJava2互相转换
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Observable<String> rxjavaOb = Observable.just("hello");
        RxJavaInterop.toV2Flowable(rxjavaOb)
                .observeOn(io.reactivex.android.schedulers.AndroidSchedulers.mainThread())
                .subscribeOn(io.reactivex.schedulers.Schedulers.io())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(@NonNull String s) throws Exception {
                        Toast.makeText(MainActivity.this, "" + s, Toast.LENGTH_SHORT).show();
                    }
                });

        Flowable<Long> rxjava2Ob = Flowable
                .interval(2, 3, TimeUnit.SECONDS);
        RxJavaInterop.toV1Observable(rxjava2Ob)
                .observeOn(rx.android.schedulers.AndroidSchedulers.mainThread())
                .subscribeOn(rx.schedulers.Schedulers.io())
                .subscribe(new Action1<Long>() {
                    @Override
                    public void call(Long aLong) {
                        Toast.makeText(MainActivity.this, "" + aLong, Toast.LENGTH_SHORT).show();
                    }
                });

        Observable.just("")
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {

                    }
                });
    }
}
