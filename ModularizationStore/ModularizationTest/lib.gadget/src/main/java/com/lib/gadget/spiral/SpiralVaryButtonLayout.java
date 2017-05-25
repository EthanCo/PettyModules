package com.lib.gadget.spiral;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;

import com.ethanco.spiralgroup.SpiralExecutor;
import com.ethanco.spiralgroup.abs.ISpiralItem;
import com.ethanco.spiralgroup.abs.OnCheckedChangeListener;
import com.ethanco.varybuttonlayout.VaryButtonLayoutPuppet;

/**
 * @Description 用作SpiralGroup子控件的VaryButtonLayout(多状态选择控件)
 * Created by EthanCo on 2016/11/11.
 */

public class SpiralVaryButtonLayout extends VaryButtonLayoutPuppet implements ISpiralItem, View.OnClickListener {
    private SpiralExecutor executor = new SpiralExecutor();
    private boolean isCheckTemp;

    public SpiralVaryButtonLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public SpiralVaryButtonLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray ta = context.obtainStyledAttributes(attrs, com.ethanco.spiralgroup.R.styleable.SpiralRelativeLayout);
        isCheckTemp = ta.getBoolean(com.ethanco.spiralgroup.R.styleable.SpiralRelativeLayout_spiralChecked, false);
        ta.recycle();
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        if (null == executor.getChild()) {
            executor.setViewAndParent(this);
            setClickable(true);
            setOnClickListener(this);
            setChecked(isCheckTemp);
        }
    }

    @Override
    public void setChecked(boolean isCheck) {
        executor.setChecked(isCheck);
    }

    @Override
    public boolean isChecked() {
        return executor.isChecked();
    }

    @Override
    public void toggle() {
        executor.toggle();
    }

    @Override
    public void onClick(View view) {
        executor.onClick(view);
    }

    @Override
    public boolean performClick() {
        executor.onClick(this);
        return true;
    }

    @Override
    public void addOnCheckedChangeListener(OnCheckedChangeListener onCheckedChangeListener) {
        executor.addOnCheckedChangeListener(onCheckedChangeListener);
    }
}