package com.ethanco.diffutiltest;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ethanco.diffutiltest.databinding.ItemBinding;

import java.util.List;

/**
 * Created by EthanCo on 2016/9/5.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ItemViewHodler> {
    public static final String KEY_TITLE = "MyDiffCallback_key_title";
    public static final String KEY_CONTENT = "MyDiffCallback_key_content";
    public static final String KEY_FOOTER = "MyDiffCallback_key_footer";

    public void setData(List<Item> data) {
        this.data = data;
    }

    private List<Item> data;
    private Context mContext;

    public MyAdapter(Context context, List<Item> data) {
        this.data = data;
        this.mContext = context;
    }

    @Override
    public ItemViewHodler onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.item, parent, false);
        return new ItemViewHodler(view);
    }

    @Override
    public void onBindViewHolder(ItemViewHodler holder, int position) {
        holder.bindData(data.get(position));
    }

    @Override
    public void onBindViewHolder(ItemViewHodler holder, int position, List<Object> payloads) {
        super.onBindViewHolder(holder, position, payloads);

        if (holder instanceof ItemViewHodler) {
            ((ItemViewHodler) holder).bindData(data.get(position));
        }
        if (payloads == null || payloads.isEmpty()) {
            return;
        }
        Bundle o = (Bundle) payloads.get(0);
        for (String key : o.keySet()) {
            switch (key) {
                case KEY_TITLE:
                    ((ItemViewHodler) holder).updateTitle(o.getString(KEY_TITLE));
                    break;
                case KEY_CONTENT:
                    ((ItemViewHodler) holder).updateContent(o.getString(KEY_CONTENT));
                    break;
                case KEY_FOOTER:
                    ((ItemViewHodler) holder).updateFooter(o.getString(KEY_FOOTER));
                    break;
            }
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class ItemViewHodler extends RecyclerView.ViewHolder {
        private final ItemBinding binding;

        public ItemViewHodler(View itemView) {
            super(itemView);

            binding = DataBindingUtil.bind(itemView);
        }

        public void bindData(Item item) {
            binding.setItem(item);
        }

        public void updateTitle(String title) {
            binding.tvTitle.setText(title);
        }

        public void updateContent(String content) {
            binding.tvContent.setText(content);
        }

        public void updateFooter(String footer) {
            binding.tvFooter.setText(footer);
        }
    }
}
