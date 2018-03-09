package com.heiko.multitextcolortest;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import loic.teillard.colortextview.ColorTextView;

public class MainActivity extends AppCompatActivity {
    private ColorTextView tvColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvColor = (ColorTextView)findViewById(R.id.tv_color);
        tvColor.addTextColor("温度", Color.parseColor("#000000"));
        tvColor.addTextColor("15°",Color.parseColor("#FF4081"));
        tvColor.addTextColor(" 湿度",Color.parseColor("#000000"));
        tvColor.addTextColor("60%",Color.parseColor("#303F9F"));
        tvColor.setSpaces(true);
        tvColor.apply();

        /*tvColor.addTextColor("Hello", Color.parseColor("#5D4037"));
        tvColor.addTextColor("world !", Color.parseColor("#F4511E"));
        tvColor.setSpaces(true);
        tvColor.apply();*/
    }
}
