package com.heiko.amaptest.clusterv3;

/**
 * 点聚合相关全局变量
 *
 * @author Heiko
 * @date 2019/2/2
 */
public class ClusterConsts {
    public static volatile boolean isCanceled = false;
    public static volatile double clusterDistance;
    //public static volatile double currMapZoom;
    public static volatile int clusterSize;
    public static volatile float pxInMeters;
    public static volatile float limitZoom = 17;
}
