package com.heiko.swipedhelper;

import android.app.Service;
import android.content.Context;
import android.graphics.Color;
import android.os.Vibrator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * ItemTouchCallBack
 *
 * @author EthanCo
 * @since 2018/5/11
 */

public class ItemTouchCallBack<T> extends ItemTouchHelper.Callback {
    private Context mContext;
    private List<T> datas = new ArrayList<>();
    private RecyclerView.Adapter adapter;
    private boolean vibratorEnable;
    private SwipedCallBack swipedCallBack;

    public ItemTouchCallBack(Context mContext, List<T> datas, RecyclerView.Adapter adapter) {
        this.mContext = mContext;
        this.datas = datas;
        this.adapter = adapter;
    }

    public void setSwipedCallBack(SwipedCallBack swipedCallBack) {
        this.swipedCallBack = swipedCallBack;
    }

    public boolean isVibratorEnable() {
        return vibratorEnable;
    }

    public void setVibratorEnable(boolean vibratorEnable) {
        this.vibratorEnable = vibratorEnable;
    }

    /*
             * 这个方法是设置是否滑动时间，以及拖拽的方向，所以在这里需要判断一下是列表布局还是网格布局，
             * 如果是列表布局的话则拖拽方向为DOWN和UP，如果是网格布局的话则是DOWN和UP和LEFT和RIGHT
             *
             * */
    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        Log.i(TAG, "onSwiped getMovementFlags");
        if (swipedCallBack != null) {
            swipedCallBack.getMovementFlags(recyclerView, viewHolder);
        }

        if (recyclerView.getLayoutManager() instanceof GridLayoutManager) {
            final int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN |
                    ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
            final int swipeFlags = 0;
            return ItemTouchHelper.Callback.makeMovementFlags(dragFlags, swipeFlags);
        } else {
            final int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
            final int swipeFlags = 0;
            return ItemTouchHelper.Callback.makeMovementFlags(dragFlags, swipeFlags);
        }
    }

    public static final String TAG = "Z-ItemTouch";

    //onMove（）方法则是我们在拖动的时候不断回调的方法
    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        Log.i(TAG, "onMove");
        if (swipedCallBack != null) {
            boolean result = swipedCallBack.onMove(recyclerView, viewHolder, target);
            if (result) {
                return true;
            }
        }
        
        //得到当拖拽的viewHolder的Position
        int fromPosition = viewHolder.getAdapterPosition();
        //拿到当前拖拽到的item的viewHolder
        int toPosition = target.getAdapterPosition();
        if (fromPosition < toPosition) {
            for (int i = fromPosition; i < toPosition; i++) {
                Collections.swap(datas, i, i + 1);
            }
        } else {
            for (int i = fromPosition; i > toPosition; i--) {
                Collections.swap(datas, i, i - 1);
            }
        }
        adapter.notifyItemMoved(fromPosition, toPosition);
        return true;
    }

    //onSwiped（）是替换后调用的方法，可以不用管
    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        Log.i(TAG, "onSwiped direction:"+direction);
        if (swipedCallBack != null) {
            swipedCallBack.onSwiped(viewHolder, direction);
        }
    }

    /**
     * 长按选中Item的时候开始调用
     *
     * @param viewHolder
     * @param actionState
     */
    @Override
    public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
        if (actionState != ItemTouchHelper.ACTION_STATE_IDLE) {
            viewHolder.itemView.setBackgroundColor(Color.LTGRAY);
            if (vibratorEnable) {
                vibrate();
            }

        }
        super.onSelectedChanged(viewHolder, actionState);
    }

    private void vibrate() {
        //获取系统震动服务
        Vibrator vib = (Vibrator) mContext.getSystemService(Service.VIBRATOR_SERVICE);
        //震动70毫秒
        vib.vibrate(70);
    }

    @Override
    public boolean isLongPressDragEnabled() {
        return super.isLongPressDragEnabled();
    }

    /**
     * 手指松开的时候还原
     * @param recyclerView
     * @param viewHolder
     */
    @Override
    public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        super.clearView(recyclerView, viewHolder);
        viewHolder.itemView.setBackgroundColor(0);
    }

    /**
     * 重写拖拽不可用
     * @return
     */
    /*@Override
    public boolean isLongPressDragEnabled() {
        return false;
    }*/
}
