package com.lib.gadget.rotate;


import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.util.AttributeSet;

import com.lib.gadget.image.SimpleRotateAnim;

/**
 * 旋转的 FloatingActionButton
 *
 * @author EthanCo
 * @since 2016/12/16
 */

public class RotateFloatingActionButton extends FloatingActionButton {
    private SimpleRotateAnim rotateAnim;

    public RotateFloatingActionButton(Context context) {
        super(context);
        init();
    }

    public RotateFloatingActionButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public RotateFloatingActionButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        rotateAnim = new SimpleRotateAnim(this, 12000);
    }

    //开始/继续 旋转动画
    public void startRotate() {
        rotateAnim.startRotate();
    }

    //暂停旋转动画
    public void pauseRotate() {
        rotateAnim.pauseRotate();
    }

    //取消旋转动画 在Activity销毁的时候需要调用，否则，会造成内存泄漏
    public void cancelRotate() {
        rotateAnim.cancelRotate();
    }

    public void setStartRotate(boolean start) {
        rotateAnim.setStartRotate(start);
    }

    public void reStartRotate() {
        rotateAnim.reStartRotate();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        rotateAnim.cancelRotate();
    }
}
