package com.heiko.amaptest.clusterv3;

import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class Cluster {

    private List<ILocation> locations;

    public Cluster(LatLng centerLatLngr) {
        this.centerLatLng = centerLatLngr;
        this.locations = new ArrayList<>();
    }

    /**
     * 中心坐标 (点聚合显示的坐标)
     */
    private LatLng centerLatLng;

    /**
     * 地图点
     */
    private Marker marker;


    public void addLocation(ILocation target) {
        if (target == null) return;
        this.locations.add(target);
    }

    public LatLng getCenterLatLng() {
        return centerLatLng;
    }

    public void setCenterLatLng(LatLng centerLatLng) {
        this.centerLatLng = centerLatLng;
    }

    public Marker getMarker() {
        return marker;
    }

    public void setMarker(Marker marker) {
        this.marker = marker;
    }

    public int getClusterCount() {
        return locations.size();
    }

    public List<ILocation> getLocations() {
        return locations;
    }
}