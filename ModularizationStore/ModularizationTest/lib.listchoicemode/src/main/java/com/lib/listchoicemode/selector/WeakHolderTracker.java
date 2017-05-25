package com.lib.listchoicemode.selector;

import android.util.SparseArray;

import com.lib.listchoicemode.abs.ISelectableHolder;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * 作者：kelingqiu on 17/1/6 15:41
 * 邮箱：42747487@qq.com
 */

class WeakHolderTracker {
    private SparseArray<WeakReference<ISelectableHolder>> mHoldersByPosition =
            new SparseArray<>();

    public ISelectableHolder getHolder(int position){
        WeakReference<ISelectableHolder> holderRef = mHoldersByPosition.get(position);
        if (holderRef == null)
            return null;

        ISelectableHolder holder = holderRef.get();
        if (holder == null || holder.getPosition() != position){
            mHoldersByPosition.remove(position);
            return null;
        }

        return holder;
    }

    public void bindHolder(ISelectableHolder holder,int position){
        mHoldersByPosition.put(position,new WeakReference<ISelectableHolder>(holder));
    }

    public List<ISelectableHolder> getTrackedHolders(){
        List<ISelectableHolder> holders = new ArrayList<>();
        for (int i = 0; i < mHoldersByPosition.size(); i++) {
            int key = mHoldersByPosition.keyAt(i);
            ISelectableHolder holder = getHolder(key);

            if (holder != null) {
                holders.add(holder);
            }
        }
        return holders;
    }


}
