package com.lib.gadget.image;

import android.content.Context;
import android.util.AttributeSet;

import com.ethanco.bindingimageview.BindingCircleImageView;

/**
 * 旋转的圆形ImageView
 *
 * @author EthanCo
 * @since 2016/12/9
 */

public class RotateCircleImageView extends BindingCircleImageView {

    private SimpleRotateAnim rotateAnim;

    public RotateCircleImageView(Context context) {
        super(context);
        init();
    }

    public RotateCircleImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public RotateCircleImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
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

    //开始/继续 旋转动画
    public void reStartRotate() {
        rotateAnim.reStartRotate();
    }

    //取消旋转动画 在Activity销毁的时候需要调用，否则，会造成内存泄漏
    public void cancelRotate() {
        rotateAnim.cancelRotate();
    }

    public void setStartRotate(boolean start) {
        rotateAnim.setStartRotate(start);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        cancelRotate();
    }
}
