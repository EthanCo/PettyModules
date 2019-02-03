package com.heiko.amaptest.clusterv3.bean;

import com.amap.api.maps.model.LatLng;
import com.heiko.amaptest.clusterv3.ILocation;

/**
 * 停车点
 *
 * @author Heiko
 * @date 2019/2/2
 */
public class ParkInfo implements ILocation {
    private LatLng latLng;

    public ParkInfo(LatLng latLng) {
        this.latLng = latLng;
    }

    @Override
    public LatLng getLocation() {
        return latLng;
    }
}
