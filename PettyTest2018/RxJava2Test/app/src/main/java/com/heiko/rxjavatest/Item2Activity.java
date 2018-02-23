package com.heiko.rxjavatest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.Toast;

import com.jakewharton.rxbinding2.view.RxView;

import java.util.concurrent.TimeUnit;

import io.reactivex.functions.Consumer;

public class Item2Activity extends AppCompatActivity {
    private Button btnClickMe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item2);

        btnClickMe = findViewById(R.id.button);
        RxView.clicks(btnClickMe)
                .throttleFirst(2, TimeUnit.SECONDS)  // 才发送 2s内第1次点击按钮的事件
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        System.out.println("--->click");
                        Toast.makeText(Item2Activity.this, "click", Toast.LENGTH_SHORT).show();
                    }
                });

    }
}
