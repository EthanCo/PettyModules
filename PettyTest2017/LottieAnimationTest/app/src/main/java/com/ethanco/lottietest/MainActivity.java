package com.ethanco.lottietest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

//吊炸天的动画，以后无需再手写繁琐的动画!
//https://gold.xitu.io/post/58948f1b0ce4630056f3a629
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
