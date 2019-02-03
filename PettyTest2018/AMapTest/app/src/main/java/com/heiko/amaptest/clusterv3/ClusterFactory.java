package com.heiko.amaptest.clusterv3;

import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.Log;

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
    public static ClusterMeta createClusterMeta(Type type, List<ILocation> positions) {
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
                public Drawable getDrawAble(int style) {
                    Drawable bitmapDrawable = mBackDrawAbles.get(style);
                    if (bitmapDrawable == null) {
                        bitmapDrawable = App.getInstance().getResources().getDrawable(
                                R.mipmap.icon_marker_enable);
                        mBackDrawAbles.put(style, bitmapDrawable);
                    }

                    return bitmapDrawable;
                }

                @Override
                public int getDrawAbleStyle(int zoom) {
                    return 1;
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
                public Drawable getDrawAble(int style) {
                    Drawable bitmapDrawable = mBackDrawAbles.get(style);
                    if (style == 1) {
                        if (bitmapDrawable == null) {
                            bitmapDrawable = App.getInstance().getResources().getDrawable(
                                    R.mipmap.ic_park_point_small);
                            mBackDrawAbles.put(style, bitmapDrawable);
                        }

                        return bitmapDrawable;
                    } else {
                        if (bitmapDrawable == null) {
                            bitmapDrawable = App.getInstance().getResources().getDrawable(
                                    R.mipmap.ic_park_point);
                            mBackDrawAbles.put(style, bitmapDrawable);
                        }
                        return bitmapDrawable;
                    }
                }

                @Override
                public int getDrawAbleStyle(int zoom) {
                    Log.i("Z-Zoom", "zoom:" + zoom+" limitZoom:"+ClusterConsts.limitZoom);
                    if (zoom >= 16) { //18
                        return 1;
                    } else {
                        return 2;
                    }
                }
            };
        }
        return parkClusterRender;
    }
}