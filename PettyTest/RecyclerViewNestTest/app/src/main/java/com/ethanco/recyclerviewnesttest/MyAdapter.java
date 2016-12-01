package com.ethanco.recyclerviewnesttest;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ethanco.recyclerviewnesttest.databinding.ItemCardBinding;

import java.util.List;

/**
 * Created by EthanCo on 2016/12/1.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ItemViewHolder> {

    private final Context context;
    private List<Info> data;

    public MyAdapter(List<Info> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        holder.binding.setInfo(data.get(position));
        holder.binding.grid.setLayoutManager(new GridLayoutManager(context, 3));
        holder.binding.grid.setAdapter(new NestAdapter(data.get(position).getImgList(), context));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {

        private final ItemCardBinding binding;

        public ItemViewHolder(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }
}
