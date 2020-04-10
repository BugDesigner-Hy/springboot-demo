package com.haiyang.kmeans.service;

import com.haiyang.kmeans.entity.Cluster;
import com.haiyang.kmeans.entity.Point;

import java.util.List;
import java.util.Set;

/**
 * @Author: HaiYang
 * @Date: 2020/4/10 14:44
 */
public interface KmeansService {
    /**
     * 初始化簇的数目K，随机选择K个中心
     * @param k
     * @param data
     * @return
     */
    List<Point> init(int k,List<Point> data);

    Set<Cluster> genClusters(List<Point> clusterPoint);

    Double distance(Point p1,Point p2);

    List<Point> genNewClusterPoint(Set<Cluster> clusters);

    boolean checkClusterPoint(List<Point> pl1,List<Point> pl2);

    boolean iteration();
}
