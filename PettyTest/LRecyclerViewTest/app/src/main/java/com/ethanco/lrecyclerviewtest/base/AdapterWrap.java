package com.ethanco.lrecyclerviewtest.base;

import com.ethanco.lrecyclerviewtest.bean.Entity;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;

/**
 * @Description Adapter 装饰
 * Created by EthanCo on 2016/9/27.
 */

public class AdapterWrap<T extends Entity> extends LRecyclerViewAdapter {
    public absListBaseAdapter<T> adapter;

    public AdapterWrap(absListBaseAdapter<T> innerAdapter) {
        super(innerAdapter);
        this.adapter = innerAdapter;
    }
}
