package com.ethanco.simpleframe.frame.view;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.transition.Explode;
import android.view.View;

/**
 * 如果不使用Fragment继承该类
 * Created by EthanCo on 2016/1/12.
 */
public abstract class BaseActivity extends AppCompatActivity {

    //transition tag
    public static final String TRANSITION_TAG1 = "tag1";
    public static final String TRANSITION_TAG2 = "tag2";
    public static final String TRANSITION_TAG3 = "tag3";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            //TODO 默认动画效果
            getWindow().setEnterTransition(new Explode());
            getWindow().setExitTransition(new Explode());
        }

    }

    public void startActivityAnimation(Intent intent, View view1, View view2, View view3) {
        ActivityCompat.startActivity(this, intent, ActivityOptionsCompat
                .makeSceneTransitionAnimation(this, Pair.create(view1, TRANSITION_TAG1),
                        Pair.create(view2, TRANSITION_TAG2), Pair.create(view3, TRANSITION_TAG3)).toBundle());
    }

    public void startActivityAnimation(Intent intent, View view1, View view2) {
        ActivityCompat.startActivity(this, intent, ActivityOptionsCompat
                .makeSceneTransitionAnimation(this, Pair.create(view1, TRANSITION_TAG1),
                        Pair.create(view2, TRANSITION_TAG2)).toBundle());
    }

    public void startActivityAnimation(Intent intent, View view1) {
        ActivityCompat.startActivity(this, intent, ActivityOptionsCompat
                .makeSceneTransitionAnimation(this, Pair.create(view1, TRANSITION_TAG1)).toBundle());
    }

    public void startActivityForResultAnimation(Intent intent, int requestCode, View view1, View view2) {
        ActivityCompat.startActivityForResult(this, intent, requestCode, ActivityOptionsCompat
                .makeSceneTransitionAnimation(this,
                        Pair.create(view1, TRANSITION_TAG1),
                        Pair.create(view2, TRANSITION_TAG2)).toBundle());
    }
}
