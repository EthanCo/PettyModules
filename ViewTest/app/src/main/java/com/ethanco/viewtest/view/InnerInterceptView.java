package com.ethanco.viewtest.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

/**
 * 内部拦截法
 * Created by EthanCo on 2016/12/26.
 */

public class InnerInterceptView extends View {
    private int mLastX;
    private int mLastY;

    public InnerInterceptView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                getParent().requestDisallowInterceptTouchEvent(true);
                LinearLayout
                break;
            case MotionEvent.ACTION_MOVE:
                int deltaX = x - mLastX;
                int deltaY = y - mLastY;
                if (isParentNeed()) { //父容器需要此点击事件
                    getParent().requestDisallowInterceptTouchEvent(false);
                }
                break;
            case MotionEvent.ACTION_UP:
                break;
            default:
        }

        mLastX = x;
        mLastY = y;
        return super.dispatchTouchEvent(event);
    }

    private boolean isParentNeed() {
        return false;
    }
}
