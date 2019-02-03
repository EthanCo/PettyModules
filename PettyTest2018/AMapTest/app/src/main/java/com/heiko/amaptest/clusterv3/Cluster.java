package com.heiko.amaptest.clusterv3;

import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class Cluster {

    private List<IPosition> positions;

    public Cluster(LatLng centerLatLngr) {
        this.latLng = centerLatLngr;
        this.positions = new ArrayList<>();
    }

    /**
     * 中心坐标 (点聚合显示的坐标)
     */
    private LatLng latLng;

    /**
     *
     */
    private Marker marker;


    public void addPosition(IPosition target) {
        if (target==null) return;
        this.positions.add(target);
    }

    public LatLng getLatLng() {
        return latLng;
    }

    public void setLatLng(LatLng latLng) {
        this.latLng = latLng;
    }

    public Marker getMarker() {
        return marker;
    }

    public void setMarker(Marker marker) {
        this.marker = marker;
    }

    public int getClusterCount() {
        return positions.size();
    }
}