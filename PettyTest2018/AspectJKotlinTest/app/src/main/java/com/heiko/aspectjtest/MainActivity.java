package com.heiko.aspectjtest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.heiko.aspectjtest.anno.TimeSpend;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnTest = findViewById(R.id.btn_test);
        btnTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*ClickAspect clickAspect = new ClickAspect();
                clickAspect.clickMethod();*/
                Log.i("Z-Test", "OnClick");
                Toast.makeText(MainActivity.this, "click Me !", Toast.LENGTH_SHORT).show();
            }
        });

        Button btnTestTime = findViewById(R.id.btn_test_time);
        btnTestTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attemptLogin();
            }
        });

        Button btnGoKotlin = findViewById(R.id.btn_go_kotlin);
        btnGoKotlin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,KotlinActivity.class);
                startActivity(intent);
            }
        });
    }

    @TimeSpend("登录")
    private void attemptLogin() {
        boolean sHasLogin = true;
        Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
    }
}
