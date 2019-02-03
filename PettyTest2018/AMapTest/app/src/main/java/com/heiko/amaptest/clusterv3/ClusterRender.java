package com.heiko.amaptest.clusterv3;

import android.graphics.drawable.Drawable;

/**
 * ClusterRender
 *
 * @author Heiko
 * @date 2019/2/2
 */
public interface ClusterRender {
    /**
     * 返回渲染背景样式
     *
     * @return
     */
    Drawable getDrawAble(int style); //int clusterNum,

    int getDrawAbleStyle(int zoom);
}
