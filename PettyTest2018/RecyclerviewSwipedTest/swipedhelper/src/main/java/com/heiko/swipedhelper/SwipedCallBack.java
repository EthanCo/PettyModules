package com.heiko.swipedhelper;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

/**
 * SimpleItemToucheCallBack
 *
 * @author EthanCo
 * @since 2018/5/11
 */

public abstract class SwipedCallBack extends ItemTouchHelper.Callback {
    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        return 0;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {

    }
}
