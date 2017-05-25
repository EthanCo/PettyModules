package com.lib.frame.model.diff;

import android.support.v7.util.DiffUtil;

import java.util.List;

/**
 * DiffUtil CallBack 封装基类
 *
 * @author EthanCo
 * @since 2016/11/21
 */

public abstract class BaseDiffCallBack<T> extends DiffUtil.Callback {

    protected List<T> oldList;
    protected List<T> newList;

    public BaseDiffCallBack(List<T> oldList, List<T> newList) {
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
}
