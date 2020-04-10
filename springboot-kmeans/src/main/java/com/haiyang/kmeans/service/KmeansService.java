package com.haiyang.kmeans.service;

import com.haiyang.kmeans.entity.Cluster;
import com.haiyang.kmeans.entity.Point;

import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: HaiYang
 * @Date: 2020/4/10 14:44
 */
public interface KmeansService {

    boolean run();

    boolean iteration();

    boolean saveCluster2db();

    Set<Cluster> getClusters();

    List<Point> genNewClusterPoint();

    Double distance(Point p1,Point p2);

    void init(int k,List<Point> data);

    void init(int k,List<Point> data,boolean save2db);

    void init(int k,List<Point> data,List<Point> clusterPoints);

    void init(int k,List<Point> data,List<Point> clusterPoints,boolean save2db);

    List<Point> getData();

    void setData(List<Point> data);

    AtomicInteger getCountIteration();

    List<Point> getClusterPoints();

    void setClusterPoints(List<Point> clusterPoints);

    Set<Cluster> genClusters(List<Point> clusterPoint);

//    boolean checkClusterPoint(List<Point> pl1,List<Point> pl2);






}
