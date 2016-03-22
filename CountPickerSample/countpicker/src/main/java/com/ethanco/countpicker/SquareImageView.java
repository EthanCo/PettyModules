package com.ethanco.countpicker;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * @Description 正方形的ImageView - 以高度为依据
 * Created by EthanCo on 2016/3/21.
 */
public class SquareImageView extends ImageView {
    public SquareImageView(Context context) {
        super(context);
    }

    public SquareImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SquareImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(heightMeasureSpec, heightMeasureSpec);
    }
}
