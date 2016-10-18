package com.ethanco.lrecyclerviewtest.base;

import android.support.v7.widget.RecyclerView;

import com.ethanco.lrecyclerviewtest.bean.Entity;

public abstract class absListBaseAdapter<T extends Entity> extends RecyclerView.Adapter implements IListBaseAdapter<T> {

}
