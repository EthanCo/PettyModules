package com.ethanco.lottietest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //LottieAnimationView animationView = (LottieAnimationView) findViewById(R.id.animation_view);
        //animationView.setAnimation("TwitterHeart.json");
        //animationView.loop(true);
    }
}
