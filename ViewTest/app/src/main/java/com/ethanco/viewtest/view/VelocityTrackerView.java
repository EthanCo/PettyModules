package com.ethanco.viewtest.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;

/**
 * Created by EthanCo on 2016/12/19.
 */

//速度追踪，用于追踪手指在滑动过程中的速度
public class VelocityTrackerView extends View {
    public static final String TAG = "Z-Velocity";
    private VelocityTracker velocityTracker;

    public VelocityTrackerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (velocityTracker == null) {
            velocityTracker = VelocityTracker.obtain();
        }
        velocityTracker.addMovement(event);

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.i(TAG, "down");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.i(TAG, "move");
                //获取速度之前必须先计算速度
                velocityTracker.computeCurrentVelocity(1000);
                //速度 = (终点位置 - 起点位置) / 时间段
                int xVelocity = (int) velocityTracker.getXVelocity();
                int yVelocity = (int) velocityTracker.getYVelocity();
                Log.i(TAG, "move xVelocity:" + xVelocity + " yVelocity:" + yVelocity);
                break;
            case MotionEvent.ACTION_UP:
                Log.i(TAG, "up");
                break;
            default:
        }
        return true;
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (velocityTracker != null) {
            velocityTracker.clear();
            velocityTracker.recycle();
            velocityTracker = null;
        }
    }
}
