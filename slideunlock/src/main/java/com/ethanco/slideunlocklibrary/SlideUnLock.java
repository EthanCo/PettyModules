package com.ethanco.slideunlocklibrary;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Scroller;

/**
 * Created by EthanCo on 2016/2/1.
 */
public class SlideUnLock extends FrameLayout {
    private Scroller mScroller;
    private boolean isUnLock = false;
    private boolean isRestore = false;
    private FrameLayout layoutContainer;

    public SlideUnLock(Context context) {
        super(context);
        init();
    }

    public SlideUnLock(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SlideUnLock(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void init() {
        //View view = LayoutInflater.from(getContext()).inflate(R.layout.layout_slide_view, null);
        View view = View.inflate(getContext(), R.layout.layout_slide_view, this);
        layoutContainer = (FrameLayout) view.findViewById(R.id.layout_slide_container);

        mScroller = new Scroller(getContext());
    }

    public void addView(View view) {
        layoutContainer.addView(view);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return super.onInterceptTouchEvent(ev);
    }

    int y;
    int lastY;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                y = (int) event.getY();
                lastY = y;
                break;
            case MotionEvent.ACTION_MOVE:
                if ((getScrollY() + (y - event.getY()) > 0)) {//防止滑到底部
                    lastY = y;
                    y = (int) event.getY();
                    scrollBy(0, (lastY - y));
                }
                break;
            case MotionEvent.ACTION_UP:
                int scrollY = getScrollY();
                if (scrollY > DensityUtils.dp2px(getContext(), 200)) {
                    isUnLock = true;
                    mScroller.startScroll(0, getScrollY(), 0, getHeight() - getScrollY());
                    invalidate(); //这句话不能忘 ------------<<<<<<<<<
                } else {
                    startRestoreScroll();
                }
                break;
            case MotionEvent.ACTION_CANCEL:
                startRestoreScroll();
                break;
            default:
        }
        return true;
    }

    private void startRestoreScroll() {
        isRestore = true;
        mScroller.startScroll(0, getScrollY(), 0, -getScrollY());
        invalidate(); //这句话不能忘 ------------<<<<<<<<<
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        //判断Scroller是否执行完毕
        if (mScroller.computeScrollOffset()) {
            scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            //通过重绘来不断调用computeScroll
            invalidate();
        } else {
            if (isUnLock) {
                isUnLock = false;
                slideUnLockListener.onSlideUnLock(true);
            } else if (isRestore) {
                isRestore = false;
                slideUnLockListener.onSlideUnLock(false);
            }
        }
    }

    public interface OnSlideUnLockListener {
        void onSlideUnLock(boolean isUnLock);
    }

    public void setOnSlideUnLockListener(OnSlideUnLockListener slideUnLockListener) {
        this.slideUnLockListener = slideUnLockListener;
    }

    public OnSlideUnLockListener slideUnLockListener = new OnSlideUnLockListener() {
        @Override
        public void onSlideUnLock(boolean b) {
            //do nothing
        }
    };
}
