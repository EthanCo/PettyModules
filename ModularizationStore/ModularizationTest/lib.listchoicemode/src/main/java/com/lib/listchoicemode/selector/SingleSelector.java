package com.lib.listchoicemode.selector;

/**
 * 作者：kelingqiu on 17/1/6 16:13
 * 邮箱：42747487@qq.com
 */

public class SingleSelector extends MultiSelector{

    @Override
    public void setSelected(int position, long id, boolean isSelected) {
        if (isSelected){
            for (Integer selectedPosition:getSelectedPositions()) {
                if (selectedPosition != position){
                    super.setSelected(selectedPosition,0,false);
                }
            }
        }
        super.setSelected(position, id, isSelected);
    }
}
