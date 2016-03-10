package com.ethanco.mylib;

import android.support.v7.widget.RecyclerView;

/**
 * @Description 圆点改变基类Adapter
 * Created by EthanCo on 2016/3/10.
 */
public abstract class PointBaseAdapter<T extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<T> {

    @Override
    public void onBindViewHolder(T holder, int position) {
        int type = getPointType(holder, position);
        switchPoint(holder, type);
        switchColor(holder, type);
        onMyBindViewholder(holder, position);
    }

    /**
     * 获取该Item Point 的 Type
     *
     * @param holder
     * @param position
     * @return
     */
    protected abstract int getPointType(T holder, int position);

    /**
     * 由于onBindViewHodler进行了一些逻辑的处理，所以剩下的在此处进行
     * @param holder
     * @param position
     */
    protected abstract void onMyBindViewholder(T holder, int position);

    /**
     * 改变颜色
     * @param holder
     * @param type
     */
    protected abstract void switchColor(T holder, int type);

    /**
     * 改变Point
     * @param holder
     * @param type
     */
    protected abstract void switchPoint(T holder, int type);
}
