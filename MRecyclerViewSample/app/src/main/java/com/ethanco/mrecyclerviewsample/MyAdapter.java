package com.ethanco.mrecyclerviewsample;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ethanco.mrecyclerview.PullOnAdapter;

import java.util.List;

/**
 * Created by Zhk on 2016/4/9.
 */
public class MyAdapter extends PullOnAdapter {
    public MyAdapter(List<String> list) {
        this.list = list;
    }

    private List<String> list;

    @Override
    protected void onBindCommonViewHolder(RecyclerView.ViewHolder holder, int position) {
        ItemViewHolder itemHolder = (ItemViewHolder) holder;
        itemHolder.tvInfo.setText(list.get(position));
    }

    @Override
    protected RecyclerView.ViewHolder onCreateCommonViewHolder(ViewGroup parent, int viewType, LayoutInflater mInflater) {
        return new ItemViewHolder(mInflater.inflate(R.layout.item, parent, false));
    }

    @Override
    protected int getItemCountM() {
        return list.size();
    }

    public void AddData(List<String> newData) {
        int start = list.size();
        list.addAll(newData);
        int end = list.size() - 1;
        notifyItemRangeChanged(start, end);
    }

    public void deleteDate(int pos) {
        list.remove(pos);
        notifyItemRemoved(pos);
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {
        private final TextView tvInfo;

        public ItemViewHolder(View itemView) {
            super(itemView);
            tvInfo = (TextView) itemView.findViewById(R.id.tvInfo);
        }
    }
}
