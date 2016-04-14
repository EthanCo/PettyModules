package com.ethanco.slidead;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.ethanco.slidead.utils.DisplayUtil;

/**
 * Created by Zhk on 2015/10/3.
 */
public class DotView extends View {
    private int minWidth = 500; //最小宽度
    private int minHeight = 200; //最小高度
    private int mWidth;
    private int mHeight;
    private int dotNumber = 4;
    private Paint focusPaint;
    private Paint unfocusPaint;
    private int currFocus = 0;

    public DotView(Context context) {
        super(context);
        init();
    }

    public DotView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public DotView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        focusPaint = new Paint();
        focusPaint.setColor(0xFF6699CC);
        focusPaint.setAntiAlias(true);
        focusPaint.setFilterBitmap(true);
        unfocusPaint = new Paint();
        unfocusPaint.setColor(0xFFFFFFFF);
        unfocusPaint.setAntiAlias(true);
        focusPaint.setFilterBitmap(true);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //mWidth = measureWidth(widthMeasureSpec);
        mWidth = DisplayUtil.dip2px(getContext(), 22) * dotNumber;
        mHeight = measureHeight(heightMeasureSpec);
        setMeasuredDimension(mWidth, mHeight);
    }

    private int measureHeight(int heightMeasureSpec) {
        int result = 0;
        int specMode = MeasureSpec.getMode(heightMeasureSpec);
        int specSize = MeasureSpec.getSize(heightMeasureSpec);

        if (specMode == MeasureSpec.EXACTLY) {
            result = specSize;
        } else {
            result = minHeight;
            if (specMode == MeasureSpec.UNSPECIFIED) {
                result = Math.min(result, specSize);
            }
        }
        return result;
    }

    private int measureWidth(int widthMeasureSpec) {
        int result = 0;
        int specMode = MeasureSpec.getMode(widthMeasureSpec);
        int specSize = MeasureSpec.getSize(widthMeasureSpec);
        if (specMode == MeasureSpec.EXACTLY) {
            result = specSize;
        } else {
            result = minWidth;
            if (specMode == MeasureSpec.UNSPECIFIED) {
                result = Math.min(result, specSize);
            }
        }
        return result;
    }

    private int getRoundPosition(int position) {
        return position % dotNumber;
    }


    /**
     * 设置点的数量
     *
     * @param number
     */
    public void setDotNumber(int number) {
        dotNumber = number;
    }

    /**
     * 现在focus的点
     *
     * @param position
     */
    public void setCurrFocus(int position) {
        currFocus = getRoundPosition(position);
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        float distance = mWidth / (float) dotNumber;
        float dw = distance / 2F;
        for (int i = 0; i < dotNumber; i++) {
            if (currFocus == i) {
                canvas.drawCircle(dw + distance * i, mHeight / 2F, mHeight / 2.2F, focusPaint);
            } else {
                canvas.drawCircle(dw + distance * i, mHeight / 2F, mHeight / 2.5F, unfocusPaint);
            }
        }
    }
}
