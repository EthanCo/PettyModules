package com.ethanco.bottomsheettest;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class MyBehavior extends CoordinatorLayout.Behavior<View> {
    public MyBehavior() {
    }

    public MyBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    private float initY = Integer.MIN_VALUE;

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {
        return dependency.getId() == R.id.scroll;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, View child, View dependency) {
        if (initY == Integer.MIN_VALUE) {
            initY = dependency.getY();
        }

        child.setTranslationY(dependency.getY() - initY);

        Log.i("MyBehavior", "dependency.getY:" + dependency.getY() + " child.getY:" + child.getY());
        Log.i("MyBehavior", "initY:" + initY);



        /*Log.i("MyBehavior", "getY:" + dependency.getY() +
                "getPivotY:" + dependency.getPivotY() +
                "getRotationY:" + dependency.getRotationY() +
                "getScrollY:" + dependency.getScrollY() +
                "getScaleY:" + dependency.getScaleY() +
                "getTranslationY:" + dependency.getTranslationY());*/
        return true;
    }
}

