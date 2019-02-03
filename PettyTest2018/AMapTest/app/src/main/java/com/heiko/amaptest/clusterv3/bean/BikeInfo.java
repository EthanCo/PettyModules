package com.heiko.amaptest.clusterv3.bean;

import com.amap.api.maps.model.LatLng;
import com.heiko.amaptest.clusterv3.ILocation;

/**
 * 车
 *
 * @author Heiko
 * @date 2019/2/2
 */
public class BikeInfo implements ILocation {
    private LatLng latLng;

    public BikeInfo(LatLng latLng) {
        this.latLng = latLng;
    }

    @Override
    public LatLng getLocation() {
        return latLng;
    }
}
