package com.ethanco.aroutertest.test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.ethanco.aroutertest.R;

@Route(path = "/test/activity2")
public class Test2Activity extends AppCompatActivity {

    private TextView tvInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test2);

        String value1 = getIntent().getStringExtra("key1");

        tvInfo = (TextView) findViewById(R.id.tv_info);
        tvInfo.append(TextUtils.isEmpty(value1) ? "" : value1);
    }
}
