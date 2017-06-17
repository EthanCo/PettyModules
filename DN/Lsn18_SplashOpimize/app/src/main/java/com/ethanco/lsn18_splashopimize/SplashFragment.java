package com.ethanco.lsn18_splashopimize;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.ScaleAnimation;
import android.widget.FrameLayout;

/**
 * Created by EthanCo on 2017/6/9.
 */

public class SplashFragment extends Fragment {
    private ScaleAnimation scaleAnimation;

    public static SplashFragment newInstance() {
        return new SplashFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_splash, container, false);
        FrameLayout frameLayout = (FrameLayout) view.findViewById(R.id.frame);
        scaleAnimation = new ScaleAnimation(1, 1.2F, 1, 1.2F,frameLayout.getX()/2.0F,frameLayout.getY()/2.0F);
        scaleAnimation.setDuration(4000);
        scaleAnimation.setRepeatCount(1);
        frameLayout.startAnimation(scaleAnimation);

        //可以在动画结束后调用Activity进行隐藏Splash，显示Main
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (scaleAnimation != null) {
            scaleAnimation.cancel();
        }
    }
}
