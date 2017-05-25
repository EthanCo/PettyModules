package com.lib.gadget.image;

/**
 * 简易旋转动画 接口
 *
 * @author EthanCo
 * @since 2016/12/16
 */

interface ISimpleRotateAnim {
    //开始/继续 旋转动画
    void startRotate();

    //暂停旋转动画
    void pauseRotate();

    //取消旋转动画 在Activity销毁的时候需要调用，否则，会造成内存泄漏
    void cancelRotate();

    void setStartRotate(boolean start);

    //开始旋转动画 每次都重新开始
    void reStartRotate();
}
