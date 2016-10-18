package com.ethanco.lrecyclerviewtest.base;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.ethanco.lrecyclerviewtest.R;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.ProgressStyle;

/**
 * Created by EthanCo on 2016/9/27.
 */

public class NovaRecyclerView extends LRecyclerView {

    //每一页展示多少条数据
    private int requestCount = 10;

    public NovaRecyclerView(Context context) {
        super(context);
        init(context);
    }

    public NovaRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public NovaRecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    private void init(Context context) {
        setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        setArrowImageView(R.drawable.ic_pulltorefresh_arrow);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        
        return super.onTouchEvent(ev);
    }
}
