package com.heiko.amaptest.clusterv2;

import com.amap.api.maps.model.LatLng;

/**
 * Created by yiyi.qi on 16/10/10.
 */

public class ParkItemV2 implements ClusterItemV2 {
    private LatLng mLatLng;
    private String mTitle;
    public ParkItemV2(LatLng latLng, String title) {
        mLatLng=latLng;
        mTitle=title;
    }

    @Override
    public LatLng getPosition() {
        return mLatLng;
    }
    public String getTitle(){
        return mTitle;
    }

}
