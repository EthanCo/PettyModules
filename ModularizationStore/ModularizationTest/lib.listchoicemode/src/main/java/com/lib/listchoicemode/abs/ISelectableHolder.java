package com.lib.listchoicemode.abs;

/**
 * 作者：kelingqiu on 17/1/6 15:43
 * 邮箱：42747487@qq.com
 */

public interface ISelectableHolder {
    void setSelectable(boolean selectable);
    boolean isSelectable();
    void setActivated(boolean activated);
    boolean isActivated();
    int getPosition();
    long getItemId();
}
