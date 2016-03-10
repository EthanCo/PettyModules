package com.ethanco.pointrecyclerview;

import android.content.Context;
import android.support.annotation.ColorInt;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ethanco.mylib.PointBaseAdapter;

import java.util.List;

/**
 * @Description TODO
 * Created by EthanCo on 2016/3/10.
 */
public class MyPointAdapter extends PointBaseAdapter<MyPointAdapter.ItemViewHolder> {
    private final List<LogisticsModel> data;

    public MyPointAdapter(List<LogisticsModel> logisticsModelList) {
        this.data = logisticsModelList;
    }

    @Override
    protected int getPointType(ItemViewHolder holder, int position) {
        if (position == 0) {
            return 0;
        } else {
            return 1;
        }
    }

    @Override
    protected void onMyBindViewholder(ItemViewHolder holder, int position) {

    }

    @Override
    protected void switchColor(ItemViewHolder holder, int type) {
        if (type == 0) {
            holder.imgPointGreen.setVisibility(View.VISIBLE);
            holder.imgPointGray.setVisibility(View.GONE);
        } else {
            holder.imgPointGreen.setVisibility(View.GONE);
            holder.imgPointGray.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void switchPoint(ItemViewHolder holder, int type) {
        Context context = holder.tvInfo.getContext();
        @ColorInt int color = context.getResources().getColor(R.color.tick_gray);
        if (type==0) {
            color = context.getResources().getColor(R.color.tick_green);
        }
        holder.tvInfo.setTextColor(color);
        holder.tvTime.setTextColor(color);
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_logistics, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {
        private final ImageView imgPointGray;
        private final ImageView imgPointGreen;
        private final View viewVerticalLine;
        private final TextView tvInfo;
        private final TextView tvTime;

        public ItemViewHolder(View itemView) {
            super(itemView);
            imgPointGray = (ImageView) itemView.findViewById(R.id.imgView_point_gray);
            imgPointGreen = (ImageView) itemView.findViewById(R.id.imgView_point_green);
            viewVerticalLine = itemView.findViewById(R.id.view_vertical_line);
            tvInfo = (TextView) itemView.findViewById(R.id.tv_logistics_info);
            tvTime = (TextView) itemView.findViewById(R.id.tv_logistics_time);
        }
    }
}
