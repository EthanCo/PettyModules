package com.ethanco.rxbindingtest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.jakewharton.rxbinding.view.RxView;

import java.util.concurrent.TimeUnit;

import rx.functions.Action1;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "Z-";
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.btn_test);

        //Button 防抖处理
        RxView.clicks(button)
                .throttleFirst(1, TimeUnit.SECONDS) //1秒之内只取一个点击事件，防抖操作
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        Log.i(TAG, "click");
                        Toast.makeText(MainActivity.this, "点击了", Toast.LENGTH_SHORT).show();
                    }
                });


        //监听长按时间
        RxView.longClicks(button)
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        Toast.makeText(MainActivity.this, "long click  ！！", Toast.LENGTH_SHORT).show();
                    }
                }) ;

    }
}
