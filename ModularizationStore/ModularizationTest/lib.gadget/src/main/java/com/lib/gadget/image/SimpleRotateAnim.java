package com.lib.gadget.image;

import android.animation.ObjectAnimator;
import android.os.Build;
import android.view.View;
import android.view.animation.LinearInterpolator;

import java.lang.ref.WeakReference;

/**
 * 旋转动画 方便使用
 *
 * @author EthanCo
 * @since 2016/12/16
 */

public class SimpleRotateAnim implements ISimpleRotateAnim {
    private int animDuration; //动画时间
    private ObjectAnimator rotateAnim;
    private WeakReference<View> targetRef;

    public SimpleRotateAnim(View target, int animDuration) {
        this.targetRef = new WeakReference<View>(target);
        this.animDuration = animDuration;
    }

    public SimpleRotateAnim(View target) {
        this(target, 12000);
    }

    //开始/继续 旋转动画
    public void startRotate() {
        if (rotateAnim == null) {
            View target = targetRef.get();
            if (target == null) return;

            initRotateAnim(target);
            rotateAnim.start();
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                rotateAnim.resume();
            } else {
                rotateAnim.start();
            }
        }
    }

    private void initRotateAnim(View target) {
        if (rotateAnim == null) {
            rotateAnim = ObjectAnimator.ofFloat(target, "rotation", 0, 360);
            rotateAnim.setDuration(animDuration);
            rotateAnim.setRepeatCount(-1);
            rotateAnim.setInterpolator(new LinearInterpolator());
        }
    }

    //暂停旋转动画
    public void pauseRotate() {
        if (rotateAnim == null) {
            return;
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            rotateAnim.pause();
        } else {
            rotateAnim.cancel();
        }
    }

    //取消旋转动画 在Activity销毁的时候需要调用，否则，会造成内存泄漏
    public void cancelRotate() {
        if (rotateAnim != null) {
            rotateAnim.cancel();
            rotateAnim = null;
        }
    }

    public void setStartRotate(boolean start) {
        if (start) {
            startRotate();
        } else {
            pauseRotate();
        }
    }

    @Override
    public void reStartRotate() {
        View target = targetRef.get();
        if (target == null) return;

        initRotateAnim(target);
        rotateAnim.start();
    }
}
