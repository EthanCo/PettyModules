package com.lib.listchoicemode.holder;

import android.support.v7.widget.RebindReportingHolder;
import android.view.View;

import com.lib.listchoicemode.abs.ISelectableHolder;
import com.lib.listchoicemode.selector.MultiSelector;

/**
 * 作者：kelingqiu on 17/1/6 16:43
 * 邮箱：42747487@qq.com
 */

public abstract class MultiSelectorBindingHolder extends RebindReportingHolder
        implements ISelectableHolder{
    private MultiSelector multiSelector;
    public MultiSelectorBindingHolder(View itemView,MultiSelector multiSelector) {
        super(itemView);
        this.multiSelector = multiSelector;
    }

    @Override
    protected void onRebind() {
        if(multiSelector != null)
            multiSelector.bindHolder(this,getLayoutPosition(),getItemId());
    }
}
