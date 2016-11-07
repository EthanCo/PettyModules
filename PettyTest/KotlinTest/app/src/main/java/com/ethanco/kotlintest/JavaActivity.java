package com.ethanco.kotlintest;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.ethanco.kotlintest._kotlin.KotlinBean;

/**
 * Created by Zhk on 2016/11/7.
 */

public class JavaActivity extends AppCompatActivity {
    private TextView tvInfo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnTest = (Button) findViewById(R.id.btn_test);
        tvInfo = (TextView) findViewById(R.id.tv_info);

        btnTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                KotlinBean bean = new KotlinBean();
                bean.setId(123);
                bean.setName("is my name");
                Toast.makeText(JavaActivity.this, bean.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
