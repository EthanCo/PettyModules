package com.ethanco.rxcachetest;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import io.rx_cache.internal.RxCache;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

//https://github.com/sungerk/RxCache/blob/master/README_ZH.md
public class MainActivity extends AppCompatActivity {

    private CacheProviders cacheProviders;
    private TextView tvInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvInfo = (TextView) findViewById(R.id.tv_info);

        cacheProviders = new RxCache.Builder()
                .persistence(getCacheDir())
                .using(CacheProviders.class);
    }

    public void onBtn1Click(View view) {
        //cacheProviders.getTime(getTextFromNet(), new DynamicKey(123456), new EvictDynamicKey(false))
        cacheProviders.getTimeSimple(getTextFromNet())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(final String result) {
                        updateUI(result);
                    }
                });
    }

    private void updateUI(final String result) {
        tvInfo.setText("result:" + result);
        Toast.makeText(MainActivity.this, "result:" + result, Toast.LENGTH_SHORT).show();
    }

    @NonNull
    private Observable<String> getTextFromNet() { //模拟从服务器获取数据
        return Observable.just("date:" + getDate()).delay(5, TimeUnit.SECONDS);
    }

    private String getDate() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(new Date());
    }
}
