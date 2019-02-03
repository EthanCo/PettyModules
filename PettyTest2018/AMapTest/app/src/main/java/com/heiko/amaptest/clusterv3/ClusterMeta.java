package com.heiko.amaptest.clusterv3;

import java.util.ArrayList;
import java.util.List;

/**
 * 点聚合 封装类
 */
public class ClusterMeta {
    private Type type;
    private ClusterRender clusterRender;
    private boolean canCluster; //是否可以点聚合
    private List<Cluster> clusters;
    private List<ILocation> locations;

    public ClusterMeta(Type type, boolean canCluster, List<ILocation> locations) {
        this.type = type;
        this.canCluster = canCluster;
        this.locations = locations;
        this.clusters = new ArrayList<>();
    }

    public List<Cluster> getClusters() {
        return clusters;
    }

    public void addCluster(Cluster cluster) {
        this.clusters.add(cluster);
    }

    public void addClusters(List<Cluster> clusters) {
        this.clusters = clusters;
    }

    public void clearClusters() {
        this.clusters.clear();
    }

    public void removeCluster(Cluster cluster) {
        this.clusters.remove(cluster);
    }

    public void removeClusters(List<Cluster> cluster) {
        this.clusters.remove(cluster);
    }

    public Type getType() {
        return type;
    }

    public boolean canCluster() {
        return canCluster;
    }

    public void setCanCluster(boolean canCluster) {
        this.canCluster = canCluster;
    }

    public ClusterRender getClusterRender() {
        return clusterRender;
    }

    public void setClusterRender(ClusterRender clusterRender) {
        this.clusterRender = clusterRender;
    }

    public List<ILocation> getLocations() {
        return locations;
    }
}