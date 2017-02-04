package com.ethanco.kotlintest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends AppCompatActivity {

    private Button btnJava;
    private Button btnKotlin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        btnJava = (Button) findViewById(R.id.btn_java);
        btnKotlin = (Button) findViewById(R.id.btn_kotlin);

        btnJava.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, JavaActivity.class);
                startActivity(intent);
            }
        });

        btnKotlin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, KotlinExtraActivity.class);
                startActivity(intent);
            }
        });
    }
}
