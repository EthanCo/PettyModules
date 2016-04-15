package com.ethanco.gridcontainerviewsample;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

/**
 * @Description 格子容器View
 * Created by EthanCo on 2016/4/15.
 */
public class GridContainerView extends LinearLayout {
    public static final int lineHeight = 300; //span高度 dp
    public static final int spanCount = 3; //列数
    private LayoutInflater mInflater;
    private LinearLayout currLineView; //现在所在列的View
    private int currLineSpanIndex = 0; //所在列的最后一个span

    public GridContainerView(Context context) {
        super(context);
        init(context);
    }

    public GridContainerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public GridContainerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        setOrientation(LinearLayout.VERTICAL);
        mInflater = LayoutInflater.from(context);
    }

    protected void addNewLine() {
        LinearLayout newLine = new LinearLayout(getContext());
        LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, lineHeight);
        newLine.setLayoutParams(lp);
        addView(newLine);
        currLineView = newLine;
        currLineSpanIndex = 0;
        if (spanCount < 0) {
            throw new IllegalStateException("spanCount must > 0");
        }
        for (int i = 0; i < spanCount; i++) {
            addNewSpan();
        }
    }

    protected void addNewSpan() {
        //View span = mInflater.inflate(R.layout.span, currLineView, false);
        FrameLayout span = new FrameLayout(getContext());
        LayoutParams spanLp = new LayoutParams(0, LayoutParams.MATCH_PARENT);
        spanLp.weight = 1;
        span.setLayoutParams(spanLp);
        currLineView.addView(span);
    }

    public void attachNewSpan(View entityView) {
        if (null == currLineView) {
            addNewLine();
        }
        if (currLineSpanIndex < spanCount) {
            ViewGroup span = (ViewGroup) currLineView.getChildAt(currLineSpanIndex);
            FrameLayout.LayoutParams entityLp = new FrameLayout.LayoutParams(
                    FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
            entityLp.gravity = Gravity.CENTER;
            entityView.setLayoutParams(entityLp);
            span.addView(entityView);
            currLineSpanIndex++;
        } else {
            addNewLine();
            attachNewSpan(entityView);
        }
    }
}
