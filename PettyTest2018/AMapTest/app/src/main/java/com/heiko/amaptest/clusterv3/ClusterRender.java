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
     * 根据聚合点的元素数目返回渲染背景样式
     *
     * @param clusterNum
     * @return
     */
    Drawable getDrawAble(int clusterNum);
}
