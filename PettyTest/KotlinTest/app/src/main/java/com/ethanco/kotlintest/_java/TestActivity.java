package com.ethanco.kotlintest._java;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.ethanco.kotlintest.R;

/**
 * Created by Zhk on 2016/11/7.
 */

public class TestActivity extends AppCompatActivity {
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
                Toast.makeText(TestActivity.this, "Hello", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
