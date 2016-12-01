package com.ethanco.recyclerviewnesttest;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.ethanco.recyclerviewnesttest.databinding.ItemImgBinding;

import java.util.List;

/**
 * Created by EthanCo on 2016/12/1.
 */

public class NestAdapter extends RecyclerView.Adapter<NestAdapter.GridITemViewHolder> {

    private final Context context;
    private List<String> data;

    public NestAdapter(List<String> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @Override
    public GridITemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_img, parent, false);
        return new GridITemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(GridITemViewHolder holder, int position) {
        Glide.with(context).load(data.get(position)).placeholder(R.mipmap.ic_launcher).centerCrop().into(holder.binding.img);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class GridITemViewHolder extends RecyclerView.ViewHolder {
        private final ItemImgBinding binding;

        public GridITemViewHolder(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }
}
