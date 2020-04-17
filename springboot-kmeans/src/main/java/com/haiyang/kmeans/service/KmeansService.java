package com.haiyang.kmeans.service;

import com.haiyang.kmeans.entity.Cluster;
import com.haiyang.kmeans.entity.Distortion;
import com.haiyang.kmeans.entity.Point;

import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: HaiYang
 * @Date: 2020/4/10 14:44
 */
public interface KmeansService {

    void kmeans(int k, List<Point> data, boolean save2db, Long dataId);

    Set<Cluster> getClusters();

    AtomicInteger getCountIteration();

    Double getDistortions();

    Set<Cluster> getClusters(int k,Long dataId);

    List<Distortion> getKLine(Long dataId);

    int bestK(Long dataId);


}
