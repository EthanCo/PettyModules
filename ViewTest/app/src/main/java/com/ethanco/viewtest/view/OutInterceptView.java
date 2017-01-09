package com.ethanco.viewtest.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.FrameLayout;

/**
 * 外部拦截
 * Created by EthanCo on 2016/12/26.
 */

public class OutInterceptView extends FrameLayout {

    private int mLastXIntercept;
    private int mLastYIntercept;

    public OutInterceptView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptHoverEvent(MotionEvent event) {
        boolean intercepted = false;
        int x = (int) event.getX();
        int y = (int) event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                //必须为false
                //NOTE:如果在ACTION_DOWN返回true，那么后续的ACTION_MOVE和ACTION_UP
                //事件都会直接交给父容器处理，这个时候就没法再传递给子元素了
                intercepted = false;
                break;
            case MotionEvent.ACTION_MOVE:
                if (isParentNeed()) { //父容器需要当前点击事件
                    intercepted = true;
                } else {
                    intercepted = false;
                }
                break;
            case MotionEvent.ACTION_UP:
                //必须返回false，因为ACTION_UP本身没有太多意义
                intercepted = false;
                break;
            default:
                break;
        }

        mLastXIntercept = x;
        mLastYIntercept = y;
        return intercepted;
    }

    private boolean isParentNeed() {
        return false;
    }
}
