package com.ethanco.nova;

import android.view.View;

import com.ethanco.nova.adapter.BaseAdapter;
import com.ethanco.nova.bean.Entity;
import com.github.jdsjlzx.recyclerview.LuRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description AdapterWrap
 * Created by EthanCo on 2016/9/28.
 */

public class AdapterWrap<T extends Entity> extends LuRecyclerViewAdapter {
    private BaseAdapter<T> adapter;

    public BaseAdapter<T> getAdapter() {
        return adapter;
    }

    public AdapterWrap(BaseAdapter<T> innerAdapter) {
        super(innerAdapter);

        this.adapter = innerAdapter;

        super.setOnItemClickListener(new com.github.jdsjlzx.interfaces.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int i) {
                for (OnItemClickListener itemClickListener : itemClickListeners) {
                    itemClickListener.onItemClick(view, i);
                }
            }

            @Override
            public void onItemLongClick(View view, int i) {
                for (OnItemLongClickListener itemLongClickListener : itemLongClickListeners) {
                    itemLongClickListener.onLongItemClick(view, i);
                }
            }
        });
    }

    public interface OnItemLongClickListener {
        void onLongItemClick(View v, int position);
    }

    public interface OnItemClickListener {
        void onItemClick(View v, int position);
    }

    protected List<OnItemClickListener> itemClickListeners = new ArrayList<>();

    protected List<OnItemLongClickListener> itemLongClickListeners = new ArrayList<>();

    public void addOnItemClickListener(OnItemClickListener listener) {
        if (!itemClickListeners.contains(listener))
            itemClickListeners.add(listener);
    }

    public void addOnItemLongClickListener(OnItemLongClickListener listener) {
        if (!itemLongClickListeners.contains(listener))
            itemLongClickListeners.add(listener);
    }

    @Override
    @Deprecated
    public void setOnItemClickListener(com.github.jdsjlzx.interfaces.OnItemClickListener mOnItemClickListener) {
        throw new IllegalStateException("please use addItemClickListener or addLongClickListener");
    }
}
