package com.haiyang.kmeans.service;/**
 * @Author: HaiYang
 * @Date: 2020/4/10 14:44
 */

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.RandomUtil;
import com.haiyang.kmeans.entity.Cluster;
import com.haiyang.kmeans.entity.Point;
import com.haiyang.kmeans.mapper.ClusterMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * @author: Administrator
 * @Date: 2020/4/10 14:44
 * @Description:
 */
@Service
@Slf4j
public class KmeansServiceImpl implements KmeansService {

    @Resource
    ClusterMapper clusterMapper;

    //数据簇数量
    private int k;

    //簇的中心点集合
    private List<Point> clusterPoints;

    //所有原始数据
    private List<Point> data = new ArrayList<>();

    private Cluster curCluster;

    //簇对象集合
    private Set<Cluster> clusters;

    //是否迭代标志
    private boolean iteration = true;

    //初始化状态
    private boolean init = false;

    //迭代次数记录
    private AtomicInteger countIteration;

    //是否将cluster数据集入库
    private boolean save2db = false;

    /**
     * 初始化数据 前期数据准备工作
     * 并且随机选出K个中心点
     *
     * @param k
     * @param data
     */
    @Override
    public void init(int k, List<Point> data) {
        this.k = k;
        this.data = clonePointList(data);
        this.clusterPoints = new ArrayList<>();
        this.iteration = true;
        this.countIteration = new AtomicInteger(0);
        for (int i = 0; i < this.k; i++) {
            int randomIndex = RandomUtil.randomInt(0, data.size());
            clusterPoints.add(data.get(randomIndex));
            data.remove(data.get(randomIndex));
        }
        this.init = true;
    }

    @Override
    public void init(int k, List<Point> data, boolean save2db) {
        init(k, data);
        this.save2db = save2db;
    }

    @Override
    public void init(int k, List<Point> data, List<Point> clusterPoints) {
        init(k, data);
        this.clusterPoints = clonePointList(clusterPoints);
    }

    @Override
    public void init(int k, List<Point> data, List<Point> clusterPoints, boolean save2db) {
        init(k, data, clusterPoints);
        this.save2db = save2db;
    }

    /**
     * 实现point对象列表的深拷贝
     *
     * @param data
     * @return
     */
    private List<Point> clonePointList(List<Point> data) {
        List<Point> res = new ArrayList<>();
        data.forEach(point -> {
            try {
                res.add(point.clone());
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
        });
        return res;
    }

    /**
     * 生成簇对象集合
     *
     * @param clusterPoint
     * @return
     */
    @Override
    public Set<Cluster> genClusters(List<Point> clusterPoint) {
        Set<Cluster> clusters = new HashSet<Cluster>(0);
        this.clusterPoints = clonePointList(clusterPoint);
        List<Point> DataFilter = null;
        DataFilter = this.data.stream().filter(point -> !this.clusterPoints.contains(point)).collect(Collectors.toList());
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
            } else {
                Cluster cluster = new Cluster();
                cluster.setClusterPoint(bestClusterPoint);
                List<Point> var1 = new ArrayList<>();
                var1.add(point);
                cluster.setData(var1);
                clusters.add(cluster);
            }
        });
        this.clusters = clusters;
        return clusters;
    }

    /**
     * 生成新的中心点集合
     *
     * @return
     */
    @Override
    public List<Point> genNewClusterPoint() {
        log.info("ClusterPoint:{}", this.clusterPoints);
        List<Point> points = new ArrayList<>();
        this.clusters.forEach(cluster -> {
            Double sum_pointX = cluster.getData().stream().map(point -> point.getX()).reduce(0D, (a, b) -> a + b);
            Double avg_pointX = sum_pointX / cluster.getData().size();
            Double sum_pointY = cluster.getData().stream().map(point -> point.getY()).reduce(0D, (a, b) -> a + b);
            Double avg_pointY = sum_pointY / cluster.getData().size();

            BigDecimal bg_x = new BigDecimal(avg_pointX);
            avg_pointX = bg_x.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
            BigDecimal bg_y = new BigDecimal(avg_pointY);
            avg_pointY = bg_y.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
            points.add(new Point(avg_pointX, avg_pointY));
        });
        if (this.clusterPoints.containsAll(points)) {
            this.iteration = false;
        } else {
            this.clusterPoints = clonePointList(points);
        }
        return points;
    }

    /**
     * 算法主启动类
     *
     * @return
     */
    @Override
    public boolean run() {
        List<Point> iterClusterPoint;
        do {
            iterClusterPoint = this.clusterPoints;
            // 计算每个点到中心点的距离 将点划分到簇
            genClusters(iterClusterPoint);
            // 计算簇中各维度的加权平均值 作为新的中心点
            genNewClusterPoint();
            //迭代加一
            this.countIteration.incrementAndGet();
        } while (iteration());
        // 重复上述步骤 直到中心点不再变化
        //将簇数据集入库
        if(this.save2db){
            saveCluster2db();
        }
        return true;
    }

    @Override
    public boolean iteration() {
        return this.iteration;
    }

    public boolean checkClusterPoint(List<Point> pl1, List<Point> pl2) {
        return pl1.containsAll(pl2);
    }

    /**
     * 选出各个数据点到中心点的最佳数据点
     *
     * @param map
     * @return
     */
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

    /**
     * 实现了欧式距离
     *
     * @param p1
     * @param p2
     * @return
     */
    @Override
    public Double distance(Point p1, Point p2) {
        return Math.sqrt(Math.abs((p1.getX() - p2.getX()) * (p1.getX() - p2.getX()) + ((p1.getY() - p2.getY()) * (p1.getY() - p2.getY()))));
    }

    /**
     * 将簇数据存入数据库
     *
     * @return
     */
    @Override
    public boolean saveCluster2db() {
        Snowflake snowflake = IdUtil.createSnowflake(1, 1);
        clusters.forEach(cluster -> {
            cluster.getData().forEach(point -> {
                cluster.setId(snowflake.nextId());
                cluster.setK(k);
                cluster.setX(cluster.getClusterPoint().getX());
                cluster.setY(cluster.getClusterPoint().getY());
                cluster.setPointId(point.getId());
                clusterMapper.insert(cluster);
            });
        });
        return true;
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

    public Set<Cluster> getClusters() {
        return clusters;
    }

    public AtomicInteger getCountIteration() {
        return countIteration;
    }
}
