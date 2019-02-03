package com.heiko.amaptest.clusterv3;

import android.content.Context;
import android.support.annotation.Nullable;

import java.util.List;

/**
 * 点聚合封装对象 工厂
 */
public class ClusterFactory {
    private static ClusterRenderFactory clusterRenderFactory = new ClusterRenderFactory();
    /**
     * @return
     */
    @Nullable
    public static ClusterMeta createClusterMeta(Type type, List<IPosition> positions, Context context) {
        if (type == Type.BIKE) {
            ClusterMeta clusterMeta = new ClusterMeta(type, positions); //positions可去除
            clusterMeta.setClusterRender(clusterRenderFactory.createBikeClusterRender(context));
            return clusterMeta;
        } else if (type == Type.PARK) {
            ClusterMeta clusterMeta = new ClusterMeta(type, positions); //positions可去除
            clusterMeta.setClusterRender(clusterRenderFactory.createParkClusterRender(context));
            return clusterMeta;
        } else {
            throw new IllegalArgumentException("not support this type:" + type);
        }
    }



}