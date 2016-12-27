package com.ethanco.proidplugintest.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

/**
 * 对RecyclerView.Adapter的封装
 * 点击事件及长按删除
 * <p>
 * Created by EthanCo on 2016/1/23.
 */
public abstract class BaseAdapter<VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {

    public static final int TYPE_HEAD = -201601001;
    public static final int TYPE_FOOT = -201601002;
    public static final int TYPE_IITEM = -201601003;

    @Deprecated
    public BaseAdapter() {
    }

    protected Context mContenxt;
    protected LayoutInflater mInflater;

    public BaseAdapter(Context contenxt) {
        this.mContenxt = contenxt;
        this.mInflater = LayoutInflater.from(contenxt);
    }

    public interface onLongClickItemListener {
        boolean onLongItemClick(View v, int position);
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

    protected onLongClickItemListener mLongItemLickListener = new onLongClickItemListener() {
        @Override
        public boolean onLongItemClick(View v, int position) {
            return false;
        }
    };

    protected onClickItemListener mItemClickListener = new onClickItemListener() {
        @Override
        public void onItemClick(View v, int position) {
            //do nothing
        }
    };

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
