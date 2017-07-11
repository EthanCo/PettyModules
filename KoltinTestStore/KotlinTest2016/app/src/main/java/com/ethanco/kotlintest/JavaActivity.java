package com.ethanco.kotlintest;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.ethanco.kotlintest._kotlin.KotlinBean;
import com.nbhope.hopelauncher.smart.view.dialog.SeekBarDialogKt;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;

/**
 * Created by Zhk on 2016/11/7.
 */

public class JavaActivity extends AppCompatActivity {
    private TextView tvInfo;
    private Button btnShowSeekBarDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnTest = (Button) findViewById(R.id.btn_test);
        tvInfo = (TextView) findViewById(R.id.tv_info);
        btnShowSeekBarDialog = (Button) findViewById(R.id.btn_show_seekbar_dialog);

        btnTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //JavaBean bean = new JavaBean();
                KotlinBean bean = new KotlinBean();
                bean.setId(123);
                bean.setName("is my name");
                Toast.makeText(JavaActivity.this, bean.toString(), Toast.LENGTH_SHORT).show();
            }
        });

        btnShowSeekBarDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            SeekBarDialogKt.showSeekBarDialog(JavaActivity.this,10,100, R.layout.layout_number_progressbar, new Function1<Integer, Unit>() {
                @Override
                public Unit invoke(Integer integer) {
                    Toast.makeText(JavaActivity.this, "value:" + integer, Toast.LENGTH_SHORT).show();
                    return Unit.INSTANCE;
                }
            });
            }
        });
    }
}
