package com.heiko.amaptest.clusterv2;

import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yiyi.qi on 16/10/10.
 */

public class ClusterV2 {
    private LatLng mLatLng;
    private List<ClusterItemV2> mClusterItems;
    private Marker mMarker;


    ClusterV2(LatLng latLng) {

        mLatLng = latLng;
        mClusterItems = new ArrayList<ClusterItemV2>();
    }

    void addClusterItem(ClusterItemV2 clusterItem) {
        mClusterItems.add(clusterItem);
    }

    int getClusterCount() {
        return mClusterItems.size();
    }



    LatLng getCenterLatLng() {
        return mLatLng;
    }

    void setMarker(Marker marker) {
        mMarker = marker;
    }

    Marker getMarker() {
        return mMarker;
    }

    List<ClusterItemV2> getClusterItems() {
        return mClusterItems;
    }
}
