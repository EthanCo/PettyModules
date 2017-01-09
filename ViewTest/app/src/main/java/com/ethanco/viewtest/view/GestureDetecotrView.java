package com.ethanco.viewtest.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by EthanCo on 2016/12/19.
 */

//手势监测，用于辅助监测用户的点击、滑动、长按、双击等行为
//OnGestureListener   监听单击、滑动、长按等行为
//OnDoubleTapListener 监听双击行为
public class GestureDetecotrView extends View implements GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener {
    public static final String TAG = "Z-Gesture";
    private GestureDetector mGestureDetector;

    public GestureDetecotrView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (mGestureDetector == null) {
            mGestureDetector = new GestureDetector(getContext(), this);
            //解决长按屏幕后无法拖动的现象
            mGestureDetector.setIsLongpressEnabled(false);
        }

        //接管目标View的onTOuchEvent方法
        return mGestureDetector.onTouchEvent(event);
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public boolean onDoubleTap(MotionEvent motionEvent) {
        Log.i(TAG, "onDoubleTap");
        return false;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent motionEvent) {
        Log.i(TAG, "onDoubleTapEvent");
        return false;
    }

    @Override
    public boolean onDown(MotionEvent motionEvent) {
        Log.i(TAG, "onDown");
        return true; //返回true才会触发其他的
    }

    @Override
    public void onShowPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        Log.i(TAG, "onSingleTapUp");
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        Log.i(TAG, "onScroll");
        return false;
    }

    @Override
    public void onLongPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        return false;
    }
}
