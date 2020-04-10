package com.haiyang.kmeans.service;/**
 * @Author: HaiYang
 * @Date: 2020/4/10 14:44
 */

import cn.hutool.core.util.RandomUtil;
import com.haiyang.kmeans.entity.Cluster;
import com.haiyang.kmeans.entity.Point;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.core.CollectionUtils;
import org.springframework.stereotype.Service;


import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

/**
 * @author: Administrator
 * @Date: 2020/4/10 14:44
 * @Description:
 */
@Service
@Slf4j
public class KmeansServiceImpl implements KmeansService {

    private int k;

    private List<Point> clusterPoints = new ArrayList<>();

    private List<Point> data = new ArrayList<>();

    private Cluster curCluster;

    private Double clusterPointX;

    private Double clusterPointY;

    private boolean iteration = true;

    @Override
    public List<Point> init(int k, List<Point> data) {
        this.k = k;
        this.data = clonePointList(data);
        for (int i = 0; i < this.k; i++) {
            int randomIndex = RandomUtil.randomInt(0, data.size());
            log.info("randomIndex:{}",randomIndex);
            clusterPoints.add(data.get(randomIndex));
            log.info("randomPoint:{}",data.get(randomIndex));
            data.remove(data.get(randomIndex));
            this.clusterPointX = data.get(randomIndex).getX();
            this.clusterPointY = data.get(randomIndex).getY();
        }
        log.info("random:{}",this.data);
        return clusterPoints;
    }

    private List<Point> clonePointList(List<Point> data){
        List<Point> res = new ArrayList<>();
        data.forEach(point ->{
            try {
                res.add(point.clone());
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
        });
        return res;
    }

    @Override
    public Set<Cluster> genClusters(List<Point> clusterPoint) {
        this.clusterPoints = clusterPoint;
        log.info("this.data:{}",this.data);
        List<Point> DataFilter = null;
        DataFilter = this.data.stream().filter(point -> !this.clusterPoints.contains(point)).collect(Collectors.toList());
        log.info("afterFilter:{}",DataFilter);
        Set<Cluster> clusters = new HashSet<Cluster>(0);;
        DataFilter.forEach(point -> {
            TreeMap<Double, Point> resMap = new TreeMap<>();
            this.clusterPoints.forEach(cluPoint -> {
                Double distance = distance(cluPoint, point);
                resMap.put(distance, cluPoint);
            });
            Point bestClusterPoint = bestClusterPoint(resMap);
            if (containClusterPoint(bestClusterPoint, clusters)) {
                clusters.remove(this.curCluster);
                this.curCluster.getData().add(point);
                clusters.add(this.curCluster);
                log.info("aaa:{}",clusters);
            } else {
                Cluster cluster = new Cluster();
                cluster.setClusterPoint(bestClusterPoint);
                List<Point> var1 = new ArrayList<>();
                var1.add(point);
                cluster.setData(var1);
                clusters.add(cluster);
                log.info("bbb:{}",clusters);
            }
        });
        return clusters;
    }

    @Override
    public List<Point> genNewClusterPoint(Set<Cluster> clusters) {

        List<Point> points = new ArrayList<>();
        clusters.forEach(cluster -> {
            Double sum_pointX = cluster.getData().stream().map(point -> point.getX()).reduce(0D, (a, b) -> a + b);
            Double avg_pointX = sum_pointX / cluster.getData().size();
            Double sum_pointY = cluster.getData().stream().map(point -> point.getY()).reduce(0D, (a, b) -> a + b);
            Double avg_pointY = sum_pointY / cluster.getData().size();

            BigDecimal bg_x = new BigDecimal(avg_pointX);
            avg_pointX = bg_x.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
            BigDecimal bg_y = new BigDecimal(avg_pointY);
            avg_pointY = bg_y.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
            log.info("newX:{},newY:{}",avg_pointX,avg_pointY);
            if(this.clusterPointX.equals(avg_pointX) && this.clusterPointY.equals(avg_pointY)){
                this.iteration = false;
            }else{
                this.clusterPointX = avg_pointX;
                this.clusterPointY = avg_pointY;
            }
            points.add(new Point(avg_pointX, avg_pointY));
        });
        return points;
    }

    @Override
    public boolean iteration() {
        return this.iteration;
    }

    @Override
    public boolean checkClusterPoint(List<Point> pl1, List<Point> pl2) {
        return pl1.containsAll(pl2);
    }

    private Point bestClusterPoint(TreeMap map) {
        return (Point) map.firstEntry().getValue();
    }

    private boolean containClusterPoint(Point clusterPoint, Set<Cluster> clusters) {
        if (clusters.size() == 0) {
            return false;
        }
        AtomicBoolean contain = new AtomicBoolean(false);
        clusters.forEach(cluster -> {
            if (cluster.getClusterPoint() == clusterPoint) {
                this.curCluster = cluster;
                contain.set(true);
            }
        });
        return contain.get();
    }

    @Override
    public Double distance(Point p1, Point p2) {
        return Math.sqrt(Math.abs((p1.getX() - p2.getX()) * (p1.getX() - p2.getX()) + ((p1.getY() - p2.getY()) * (p1.getY() - p2.getY()))));
    }

    public List<Point> getClusterPoints() {
        return clusterPoints;
    }

    public void setClusterPoints(List<Point> clusterPoints) {
        this.clusterPoints = clusterPoints;
    }

    public List<Point> getData() {
        return data;
    }

    public void setData(List<Point> data) {
        this.data = data;
    }
}
