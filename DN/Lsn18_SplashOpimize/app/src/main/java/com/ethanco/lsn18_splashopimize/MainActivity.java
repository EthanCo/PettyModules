package com.ethanco.lsn18_splashopimize;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewStub;

import java.lang.ref.WeakReference;

public class MainActivity extends AppCompatActivity {

    private Handler mHandler = new Handler();
    private ViewStub viewStub;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final SplashFragment splashFragment = SplashFragment.newInstance();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame, splashFragment);
        transaction.commit();

        viewStub = (ViewStub)findViewById(R.id.content_viewstub);
        //1.判断当窗体加载完毕的时候,立马再加载真正的布局进来
        getWindow().getDecorView().post(new Runnable() {

            @Override
            public void run() {
                // 开启延迟加载
                mHandler.post(new Runnable() {

                    @Override
                    public void run() {
                        //将viewstub加载进来
                        viewStub.inflate();
                    }
                } );
            }
        });

        //2.判断当窗体加载完毕的时候执行,延迟一段时间做动画。
        getWindow().getDecorView().post(new Runnable() {
            @Override
            public void run() {
                //开启延迟加载
                mHandler.postDelayed(new DelayRunnable(MainActivity.this, splashFragment), 2000);
            }
        });

        //3.同时进行异步加载数据
    }

    private static class DelayRunnable implements Runnable {
        private WeakReference<MainActivity> mainActivityRef;
        private WeakReference<SplashFragment> fragmentRef;

        public DelayRunnable(MainActivity mainActivity, SplashFragment splashFragment) {
            this.mainActivityRef = new WeakReference<>(mainActivity);
            this.fragmentRef = new WeakReference<>(splashFragment);
        }

        @Override
        public void run() {
            MainActivity mainActivity = mainActivityRef.get();
            if (mainActivity == null) return;
            SplashFragment splashFragment = fragmentRef.get();
            if (splashFragment == null) return;

            // 移除fragment
            FragmentTransaction transaction = mainActivity.getSupportFragmentManager().beginTransaction();
            transaction.remove(splashFragment);
            transaction.commit();
        }
    }
}
