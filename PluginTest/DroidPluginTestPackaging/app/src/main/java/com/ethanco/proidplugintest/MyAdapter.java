package com.ethanco.proidplugintest;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ethanco.nova.adapter.BaseAdapter;
import com.morgoo.facade.ApkItem;

/**
 * Adapter
 *
 * @author EthanCo
 * @since 2016/12/26
 */

public class MyAdapter extends BaseAdapter<ApkItem> {
    public MyAdapter(Context context) {
        super(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_main, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ItemViewHolder) {
            ItemViewHolder itemviewholder = (ItemViewHolder) holder;
            final ApkItem apkItem = mDataList.get(position);
            itemviewholder.tvInfo.setText(apkItem.getTitle());
        }
    }

    static class ItemViewHolder extends RecyclerView.ViewHolder {
        private final TextView tvInfo;

        public ItemViewHolder(View itemView) {
            super(itemView);
            tvInfo = (TextView) itemView.findViewById(R.id.tv_item_info);
        }
    }
}
