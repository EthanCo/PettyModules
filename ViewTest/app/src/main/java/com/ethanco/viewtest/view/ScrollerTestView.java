package com.ethanco.viewtest.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Scroller;

/**
 * Created by EthanCo on 2016/12/21.
 */

public class ScrollerTestView extends View {
    Scroller mScroller;
    private Paint mPaint;

    public ScrollerTestView(Context context) {
        super(context);
        init(context);
    }

    public ScrollerTestView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ScrollerTestView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        mScroller = new Scroller(context);
        mPaint = new Paint();
        mPaint.setColor(Color.BLUE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawRect(0, 0, getRight(), getBottom(), mPaint);
    }

    double lastX;
    double lastY;

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                lastX = event.getX();
                lastY = event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                scrollTo(-(int) (event.getX() - lastX), -(int) (event.getY() - lastY));
//                lastX = event.getX();
//                lastY = event.getY();
                //scrollTo(-(int) event.getX(),- (int) event.getY());
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                smoothScrollTo(100, 200);
                break;
        }

        return true;

        //return super.onTouchEvent(event);
    }

    private void smoothScrollTo(int destX, int destY) {
//        int scrollX = getScrollX();
//        int scrollY = getScrollY();
//        int deltaX = destX - scrollX;
//        int deltaY = destY - scrollY;
//
//        Log.i("Z-", "scrollX:" + scrollX + " scrollY:" + scrollY + " deltaX:" + deltaX + " deltaY:" + deltaY);

        //1000ms内滑向destX,效果就是慢慢滑动
        //mScroller.startScroll(scrollX, destY, deltaX, deltaY, 1000);
        //mScroller.startScroll(0, 0, 100, 100, 1000);

        mScroller.startScroll(getScrollX(), getScrollY(),
                -getScrollX(), -getScrollY(), 1000);

        invalidate();
    }

    @Override
    public void computeScroll() {
        if (mScroller.computeScrollOffset()) {
            scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            postInvalidate();
        }
    }
}
