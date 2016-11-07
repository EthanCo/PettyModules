package com.ethanco.diffutiltest;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;

import java.util.List;

/**
 * 最核心类，不要被命名困惑，它不像你日常所使用的回调。可以将它理解成 比较新老数据集时的规则
 * 通过覆盖特定方法给出数据比较逻辑
 * Created by EthanCo on 2016/9/5.
 */
public class MyDiffCallback extends DiffUtil.Callback {


    private List<Item> oldList;
    private List<Item> newList;

    public MyDiffCallback(List<Item> oldList, List<Item> newList) {
        this.oldList = oldList;
        this.newList = newList;
    }

    @Override
    public int getOldListSize() {
        return oldList.size();
    }

    @Override
    public int getNewListSize() {
        return newList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return oldList.get(oldItemPosition).id == newList.get(newItemPosition).id;
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return oldList.get(oldItemPosition).equals(newList.get(newItemPosition
        ));
    }

    @Nullable
    @Override
    public Object getChangePayload(int oldItemPosition, int newItemPosition) {
        Item oldItem = oldList.get(oldItemPosition);
        Item newItem = newList.get(newItemPosition);
        Bundle diffBundle = new Bundle();
        if (!newItem.title.equals(oldItem.title)) {
            diffBundle.putString(MyAdapter.KEY_TITLE, newItem.title);
        }
        if (!newItem.content.equals(oldItem.content)) {
            diffBundle.putString(MyAdapter.KEY_CONTENT, newItem.content);
        }
        if (!newItem.footer.equals(oldItem.footer)) {
            diffBundle.putString(MyAdapter.KEY_FOOTER, newItem.footer);
        }
        if (diffBundle.size() == 0)
            return null;
        return diffBundle;
    }
}
