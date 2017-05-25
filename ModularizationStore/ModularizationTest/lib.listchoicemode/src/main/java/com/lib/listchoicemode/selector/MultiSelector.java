package com.lib.listchoicemode.selector;

import android.util.SparseBooleanArray;

import com.lib.listchoicemode.abs.ISelectableHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：kelingqiu on 17/1/6 15:40
 * 邮箱：42747487@qq.com
 */

public class MultiSelector {
    private SparseBooleanArray mSelections = new SparseBooleanArray();
    private WeakHolderTracker mTracker = new WeakHolderTracker();
    private boolean mIsSelectable;

    public void setSelectable(boolean isSelectable) {
        mIsSelectable = isSelectable;
        refreshAllHolders();
    }

    public boolean isSelectable() {
        return mIsSelectable;
    }

    private void refreshAllHolders() {
        for (ISelectableHolder holder:mTracker.getTrackedHolders()) {
            refreshHolder(holder);
        }
    }

    private void refreshHolder(ISelectableHolder holder){
        if (holder == null)
            return;
        holder.setSelectable(mIsSelectable);
        boolean isActivated = mSelections.get(holder.getPosition());
        holder.setActivated(isActivated);
    }

    public boolean isSelected(int position,long id){
        return mSelections.get(position);
    }

    public void setSelected(int position, long id, boolean isSelected){
        mSelections.put(position,isSelected);
        refreshHolder(mTracker.getHolder(position));
    }

    public void cleanSelections(){
        mSelections.clear();
        refreshAllHolders();
    }

    public List<Integer> getSelectedPositions(){
        List<Integer> positions = new ArrayList<>();
        for (int i = 0; i < mSelections.size(); i++) {
            if (mSelections.valueAt(i))
            positions.add(mSelections.keyAt(i));
        }

        return positions;
    }

    public void bindHolder(ISelectableHolder holder, int position, long id){
        mTracker.bindHolder(holder,position);
        refreshHolder(holder);
    }

    public void setSelected(ISelectableHolder holder, boolean isSelected){
        setSelected(holder.getPosition(),holder.getItemId(),isSelected);
    }

    public boolean tapSelection(ISelectableHolder holder){
        return tapSelection(holder.getPosition(), holder.getItemId());
    }

    public boolean tapSelection(int position, long itemId){
        if (mIsSelectable){
            boolean isSelected = isSelected(position,itemId);
            setSelected(position, itemId, !isSelected);
            return true;
        }

        return false;
    }
}
