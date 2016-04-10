package com.ethanco.mrecyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Zhk on 2016/4/9.
 */
public abstract class MAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    
    public void setFootVisible(boolean visible) {
        if (visible) {
            footCount = 1;
        } else {
            footCount = 0;
        }
    }

    protected int footCount = 1;

    public static final int ITEM_COMMON = 888;
    public static final int ITEM_FOOT = 552;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater mInflater = LayoutInflater.from(parent.getContext());
        if (footCount == 1 && viewType == ITEM_FOOT) {
            View view = mInflater.inflate(R.layout.item_mrecyclerview_foot, parent, false);
            return new FootViewHolder(view);
        } else {
            return onCreateCommonViewHolder(parent, viewType, mInflater);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof FootViewHolder) {

        } else {
            onBindCommonViewHolder(holder, position);
        }
    }


    @Override
    public int getItemViewType(int position) {
        if (footCount > 1)
            throw new IllegalStateException("footCount > 1");
        else if (footCount == 1 && position == getItemCount() - 1)
            return ITEM_FOOT;
        else
            return ITEM_COMMON;
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

    //************************* SimpleAdapter *****************************//

    public interface onLongClickItemListener {
        void onLongItemClick(View v, int position);
    }

    public interface onClickItemListener {
        void onItemClick(View v, int position);
    }

    public void setOnLongItemLickListener(onLongClickItemListener mLongItemLickListener) {
        this.mLongItemLickListener = mLongItemLickListener;
    }

    public void setOnItemClickListener(onClickItemListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    protected onLongClickItemListener mLongItemLickListener;

    protected onClickItemListener mItemClickListener;

    /* public void AddData(int pos) {
        mDatas.add(pos, "Insert One");
        //notifyItemChanged(pos);
        //注意要调用这个，而不是上面那个，并不会刷新所有的View
        notifyItemInserted(pos);
    }*/

    /*public void deleteDate(int pos) {
        mDatas.remove(pos);
        //notifyItemChanged(pos);
        notifyItemRemoved(pos); //Remove
    }*/
}
