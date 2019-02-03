package com.heiko.amaptest.clusterv3;

import com.amap.api.maps.model.Marker;

/**
 * ClusterClickListener
 *
 * @author Heiko
 * @date 2019/2/2
 */
public interface ClusterClickListener {
    /**
     * 点击聚合点的回调处理函数
     *
     * @param marker      点击的聚合点
     * @param clusterMeta 聚合点所包含的元素
     */
    public void onClick(Marker marker, ClusterMeta clusterMeta);
}