package com.heiko.rxjavatest;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.heiko.rxjavatest.databinding.ActivityMainBinding;

/**
 * Android RxJava应用实例讲解：你该什么时候使用RxJava？
 * https://juejin.im/post/5a7b9e7cf265da4e7d602ef0
 */
public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.btnItem1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(Item1Activity.class);
            }
        });

        binding.btnItem2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(Item2Activity.class);
            }
        });

        binding.btnItem3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(Item3Activity.class);
            }
        });
    }

    private void startActivity(Class<?> targetCls) {
        Intent intent = new Intent(MainActivity.this, targetCls);
        startActivity(intent);
    }
}
