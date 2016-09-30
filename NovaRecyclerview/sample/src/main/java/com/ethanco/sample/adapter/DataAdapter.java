package com.ethanco.sample.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ethanco.nova.adapter.BaseAdapter;
import com.ethanco.sample.R;
import com.ethanco.sample.bean.ItemModel;

/**
 * @Description 基础Adapter
 * Created by EthanCo on 2016/9/27.
 */

public class DataAdapter extends BaseAdapter<ItemModel> {

    private LayoutInflater mLayoutInflater;

    public DataAdapter(Context context) {
        mLayoutInflater = LayoutInflater.from(context);
        mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(mLayoutInflater.inflate(R.layout.list_item_text, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        ItemModel item = mDataList.get(position);

        ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.textView.setText(item.title);

    }

    private class ViewHolder extends RecyclerView.ViewHolder {

        private TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.info_text);
        }
    }
}
