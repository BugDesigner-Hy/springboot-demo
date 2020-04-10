package com.haiyang.kmeans.controller;/**
 * @Author: HaiYang
 * @Date: 2020/4/10 14:37
 */

import com.haiyang.kmeans.entity.Cluster;
import com.haiyang.kmeans.entity.Point;
import com.haiyang.kmeans.mapper.PointMapper;
import com.haiyang.kmeans.service.KmeansService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import sun.invoke.empty.Empty;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * @author: Administrator
 * @Date: 2020/4/10 14:37
 * @Description:
 */
@Slf4j
@RestController
public class DataController {

    @Autowired
    KmeansService kmeansService;

    @Autowired
    PointMapper pointMapper;

    @GetMapping("/kmeans")
    public Set<Cluster> kmeans() {
        log.info("=================K-means start====================");
        List<Point> data = pointMapper.selectList(null);
        //  初始化簇的数目K 选出K个中心点
        List<Point> initClusterPoints = kmeansService.init(2, data);
        List<Point> newClusterPoints = null;
        List<Point> iterClusterPoint;
        Set<Cluster> clusters = null;
        boolean init = true;
        int i = 1;
        do {
            if(init){
                iterClusterPoint = initClusterPoints;
            }else {
                iterClusterPoint = newClusterPoints;
            }
            // 计算每个点到中心点的距离 将点划分到簇
            clusters = kmeansService.genClusters(iterClusterPoint);
            log.info("clusters.size:{}",clusters.size());
            // 输出簇及簇下数据
            log.info(clusters.toString());
            // 计算簇中各维度的加权平均值 作为新的中心点
            newClusterPoints = kmeansService.genNewClusterPoint(clusters);
            log.info("initPoint:{}",initClusterPoints);
            log.info("newPoint:{}",newClusterPoints);
            init = false;
            i++;
        } while (kmeansService.iteration());        // 重复上述2,3步骤 直到中心点不再变化
        log.info("=============K-means finish====================");
        return clusters;

    }
}
