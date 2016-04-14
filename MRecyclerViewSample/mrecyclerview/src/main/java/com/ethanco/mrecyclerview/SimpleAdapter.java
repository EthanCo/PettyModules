package com.ethanco.mrecyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * 对RecyclerView.Adapter的简易封装
 * 点击事件及长按删除
 * <p>
 * Created by EthanCo on 2016/1/23.
 */
public abstract class SimpleAdapter<VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {

    public static final int TYPE_HEAD = -201601001;
    public static final int TYPE_FOOT = -201601002;
    public static final int TYPE_IITEM = -201601003;

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
