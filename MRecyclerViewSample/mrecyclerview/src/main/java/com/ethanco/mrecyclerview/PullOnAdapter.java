package com.ethanco.mrecyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Zhk on 2016/4/9.
 */
public abstract class PullOnAdapter extends SimpleAdapter<RecyclerView.ViewHolder> {

    /**
     * 设置Foot是否可见
     *
     * @param visible
     */
    public void setFootVisible(boolean visible) {
        if (visible) {
            footCount = 1;
        } else {
            footCount = 0;
        }
    }

    protected int footCount = 1;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater mInflater = LayoutInflater.from(parent.getContext());
        if (footCount == 1 && viewType == TYPE_FOOT) {
            View view = mInflater.inflate(R.layout.item_mrecyclerview_foot, parent, false);
            return new FootViewHolder(view);
        } else {
            return onCreateCommonViewHolder(parent, viewType, mInflater);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof FootViewHolder) {
            onBindFootView((FootViewHolder) holder);
        } else {
            onBindCommonViewHolder(holder, position);
        }
    }

    protected void onBindFootView(FootViewHolder holder) {

    }


    @Override
    public int getItemViewType(int position) {
        if (footCount > 1)
            throw new IllegalStateException("footCount > 1");
        else if (footCount == 1 && position == getItemCount() - 1)
            return TYPE_FOOT;
        else
            return TYPE_IITEM;
    }

    @Override
    public int getItemCount() {
        return getItemCountM() + footCount;
    }

    protected abstract void onBindCommonViewHolder(RecyclerView.ViewHolder holder, int position);

    protected abstract RecyclerView.ViewHolder onCreateCommonViewHolder(ViewGroup parent, int viewType, LayoutInflater mInflater);

    protected abstract int getItemCountM();

    protected class FootViewHolder extends RecyclerView.ViewHolder {
        public FootViewHolder(View itemView) {
            super(itemView);
        }
    }
}
