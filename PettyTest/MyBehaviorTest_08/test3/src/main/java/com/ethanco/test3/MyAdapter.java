package com.ethanco.test3;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by EthanCo on 2016/8/11.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ItemViewHolder> implements ItemTouchHelperAdapter {

    private Context context;

    public MyAdapter(Context context) {
        this.context = context;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        holder.title.setText("Item" + position);
        holder.subTitle.setText("不可描述...");
    }

    @Override
    public int getItemCount() {
        return 30;
    }

    @Override
    public void onItemMove(int fromPosition, int toPosition) {
        Log.i("Z-MyAdapter", "onItemMove fromPosition:" + fromPosition + " toPosition:" + toPosition);
        notifyItemMoved(fromPosition, toPosition);
    }

    @Override
    public void onItemDismiss(int position) {
        Log.i("Z-MyAdapter", "onItemDismiss position:" + position);
        notifyItemRemoved(position);
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {

        private final TextView title;
        private final TextView subTitle;

        public ItemViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.tvTitle);
            subTitle = (TextView) itemView.findViewById(R.id.tvSubTitle);
        }
    }
}
