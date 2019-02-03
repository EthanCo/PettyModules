package com.heiko.amaptest.clusterv3;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class ClusterMeta {

    /**
     * Default constructor
     */
    public ClusterMeta(Type type, List<IPosition> positions) {
        this.type = type;
        this.positions = positions;
        this.clusters = new ArrayList<>();
    }

    /**
     *
     */
    private Type type;

    private ClusterRender clusterRender;

    /**
     *
     */
    private List<Cluster> clusters;

    /**
     *
     */
    private List<IPosition> positions;

    public List<Cluster> getClusters() {
        return clusters;
    }

    public void addCluster(Cluster cluster){
        this.clusters.add(cluster);
    }

    public void addClusters(List<Cluster> clusters){
        this.clusters = clusters;
    }

    public void clearClusters(){
        this.clusters.clear();
    }

    public Type getType() {
        return type;
    }

    public ClusterRender getClusterRender() {
        return clusterRender;
    }

    public void setClusterRender(ClusterRender clusterRender) {
        this.clusterRender = clusterRender;
    }

    public List<IPosition> getPositions() {
        return positions;
    }
}