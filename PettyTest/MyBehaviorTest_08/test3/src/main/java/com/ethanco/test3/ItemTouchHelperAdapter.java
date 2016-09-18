package com.ethanco.test3;

/**
 * Created by EthanCo on 2016/8/11.
 */
public interface ItemTouchHelperAdapter {

    void onItemMove(int fromPosition, int toPosition);

    void onItemDismiss(int position);
}
