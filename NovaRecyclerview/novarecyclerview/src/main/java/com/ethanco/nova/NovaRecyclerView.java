package com.ethanco.nova;

import android.content.Context;
import android.util.AttributeSet;

import com.ethanco.nova.adapter.BaseAdapter;
import com.ethanco.nova.base.MRecyclerView;

/**
 * @Description RecyclerView
 * Created by EthanCo on 2016/9/28.
 */

public class NovaRecyclerView extends MRecyclerView {

    private String sss;

    public NovaRecyclerView(Context context) {
        super(context);
        init(context);
        initEvent(context);
    }

    public NovaRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
        initEvent(context);
    }

    public NovaRecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
        initEvent(context);
    }

    private void init(Context context) {

    }

    private void initEvent(Context context) {

    }


    @Override
    public void setAdapter(Adapter adapter) {
        AdapterWrap adapterWrap;
        if (adapter instanceof AdapterWrap) {
            adapterWrap = (AdapterWrap) adapter;
        } else {
            adapterWrap = new AdapterWrap((BaseAdapter) adapter);

        }

        super.setAdapter(adapterWrap);
    }
}
