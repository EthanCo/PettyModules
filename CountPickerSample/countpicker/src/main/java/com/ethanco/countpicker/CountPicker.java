package com.ethanco.countpicker;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * @Description count选择器
 * Created by EthanCo on 2016/3/21.
 */
public class CountPicker extends FrameLayout {
    private ImageView imgSub;
    private ImageView imgPlus;
    private TextView tvCount;

    private int currCount = 0;
    private int minCount = 0;
    private int maxCount = 99;
    private float countTextSize;
    private int countTextColor;

    public CountPicker(Context context) {
        super(context);
        initView(context);
        initEvent();
    }

    public CountPicker(Context context, AttributeSet attrs) {
        super(context, attrs);
        initVar(context, attrs);
        initView(context);
        initEvent();
    }

    public CountPicker(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initVar(context, attrs);
        initView(context);
        initEvent();
    }

    private void initVar(Context context, AttributeSet attrs) {
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.CountPicker);
        countTextColor = ta.getColor(R.styleable.CountPicker_countTextColor, Color.BLACK);
        countTextSize = ta.getDimension(R.styleable.CountPicker_countTextSize, 0);
        minCount = ta.getInt(R.styleable.CountPicker_minCount, 0);
        maxCount = ta.getInt(R.styleable.CountPicker_maxCount, 99);
        currCount = ta.getInt(R.styleable.CountPicker_defCount, minCount);
        ta.recycle();
    }

    private void initView(Context context) {
        View view = View.inflate(context, R.layout.widget_count_picker, this);
        imgSub = (ImageView) view.findViewById(R.id.img_sub);
        imgPlus = (ImageView) view.findViewById(R.id.img_plus);
        tvCount = (TextView) view.findViewById(R.id.tv_count);

        tvCount.setTextColor(countTextColor);
        if (countTextSize > 0)
            tvCount.setTextSize(TypedValue.COMPLEX_UNIT_PX, countTextSize);
        updateCountTextView(true);
    }

    private void initEvent() {
        imgSub.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                subCount();
            }
        });
        imgPlus.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                addCount();
            }
        });
    }

    private void addCount() {
        if (currCount < maxCount) {
            currCount++;
            updateCountTextView(true);
        } else {

        }
    }

    private void subCount() {
        if (currCount > minCount) {
            currCount--;
            updateCountTextView(false);
        } else {

        }
    }

    /**
     * 监听Count改变
     */
    public interface onCountChangeListener {
        void onCountChange(boolean isAdd, int count);
    }

    public void setCountChangeListener(onCountChangeListener mCountChangeListener) {
        this.mCountChangeListener = mCountChangeListener;
    }

    private onCountChangeListener mCountChangeListener = new onCountChangeListener() {
        @Override
        public void onCountChange(boolean isAdd, int count) {
            //do nothing
        }
    };

    private void updateCountTextView(boolean isAdd) {
        tvCount.setText(String.valueOf(currCount));
        mCountChangeListener.onCountChange(isAdd, currCount);
    }

    public void setCurrCount(int _currCount) {
        this.currCount = _currCount;
        boolean isAdd = false;
        if (this.currCount < _currCount) {
            isAdd = true;
        }
        updateCountTextView(isAdd);
    }

    public int getCurrCount() {
        return currCount;
    }

    public int getMinCount() {
        return minCount;
    }

    public void setMinCount(int minCount) {
        this.minCount = minCount;
    }


    public int getMaxCount() {
        return maxCount;
    }

    public void setMaxCount(int maxCount) {
        this.maxCount = maxCount;
    }
}
