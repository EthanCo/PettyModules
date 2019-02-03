package com.heiko.amaptest.clusterv3;

import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;

import com.heiko.amaptest.App;
import com.heiko.amaptest.R;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 点聚合封装对象 工厂
 */
public class ClusterFactory {
    /**
     * @return
     */
    @Nullable
    public static ClusterMeta createClusterMeta(Type type, List<IPosition> positions) {
        if (type == Type.BIKE) {
            ClusterMeta clusterMeta = new ClusterMeta(type, false, positions); //positions可去除
            clusterMeta.setClusterRender(createBikeClusterRender());
            return clusterMeta;
        } else if (type == Type.PARK) {
            ClusterMeta clusterMeta = new ClusterMeta(type, true, positions); //positions可去除
            clusterMeta.setClusterRender(createParkClusterRender());
            return clusterMeta;
        } else {
            throw new IllegalArgumentException("not support this type:" + type);
        }
    }


    private static ClusterRender bikeClusterRender;
    private static ClusterRender parkClusterRender;

    static ClusterRender createBikeClusterRender() {
        if (bikeClusterRender == null) {
            bikeClusterRender = new ClusterRender() {
                private Map<Integer, Drawable> mBackDrawAbles = new HashMap<>();

                @Override
                public Drawable getDrawAble(int clusterNum) {
                    Drawable bitmapDrawable = mBackDrawAbles.get(1);
                    if (bitmapDrawable == null) {
                        bitmapDrawable = App.getInstance().getResources().getDrawable(
                                R.mipmap.icon_marker_enable);
                        mBackDrawAbles.put(1, bitmapDrawable);
                    }

                    return bitmapDrawable;
                }
            };
        }
        return bikeClusterRender;
    }

    static ClusterRender createParkClusterRender() {
        if (parkClusterRender == null) {
            parkClusterRender = new ClusterRender() {
                private Map<Integer, Drawable> mBackDrawAbles = new HashMap<>();

                @Override
                public Drawable getDrawAble(int clusterNum) {
                    if (clusterNum == 1) {
                        Drawable bitmapDrawable = mBackDrawAbles.get(1);
                        if (bitmapDrawable == null) {
                            bitmapDrawable = App.getInstance().getResources().getDrawable(
                                    R.mipmap.ic_park_point);
                            mBackDrawAbles.put(1, bitmapDrawable);
                        }

                        return bitmapDrawable;
                    } else {
                        Drawable bitmapDrawable = mBackDrawAbles.get(2);
                        if (bitmapDrawable == null) {
                            bitmapDrawable = App.getInstance().getResources().getDrawable(
                                    R.mipmap.ic_park_point);
                            mBackDrawAbles.put(2, bitmapDrawable);
                        }
                        return bitmapDrawable;
                    }
                }
            };
        }
        return parkClusterRender;
    }
}