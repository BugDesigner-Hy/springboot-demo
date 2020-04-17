package com.haiyang.kmeans.service.impl;/**
 * @Author: HaiYang
 * @Date: 2020/4/10 14:44
 */

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.TimeInterval;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.haiyang.kmeans.entity.Cluster;
import com.haiyang.kmeans.entity.Distortion;
import com.haiyang.kmeans.entity.Point;
import com.haiyang.kmeans.mapper.ClusterMapper;
import com.haiyang.kmeans.mapper.DistortionMapper;
import com.haiyang.kmeans.mapper.PointMapper;
import com.haiyang.kmeans.service.DataService;
import com.haiyang.kmeans.util.HaverSine;
import com.haiyang.kmeans.service.KmeansService;
import com.haiyang.kmeans.entity.DistanceType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
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
    DataService dataService;

    @Resource
    PointMapper pointMapper;

    @Resource
    ClusterMapper clusterMapper;

    @Resource
    DistortionMapper distortionMapper;

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

    //畸变程度
    private Double distortions = Double.MAX_VALUE;

    //记录上一条畸变
    private Double lastDistortions = 0D;

    //数据集标识
    private Long dataId = 0L;

    /**
     * 初始化数据 前期数据准备工作
     * 并且随机选出K个中心点
     *
     * @param k
     * @param data
     */
    private void init(int k, List<Point> data) {
        this.k = k;
        this.clusters = null;
        this.curCluster = null;
        this.distortions = Double.MAX_VALUE;
        this.data = clonePointList(data);
        this.clusterPoints = new ArrayList<>();
        this.iteration = true;
        this.countIteration = new AtomicInteger(0);
        for (int i = 0; i < this.k; i++) {
            int randomIndex;
            randomIndex = RandomUtil.randomInt(0, data.size() - 1);
            clusterPoints.add(data.get(randomIndex));
        }
        this.init = true;
    }

    private void init(int k, List<Point> data, boolean save2db, Long dataId) {
        init(k, data);
        this.save2db = save2db;
        this.dataId = dataId;
    }

    /**
     * 生成簇对象集合
     *
     * @param
     * @return
     */
    private Set<Cluster> genClusters() {
//        TimeInterval timer = DateUtil.timer();
        this.clusters = null;
        Set<Cluster> initclusters = new HashSet<Cluster>(0);
        List<Point> DataFilter;
        List<Point> finalVar = clonePointList(this.clusterPoints);
        ConcurrentHashMap<Point, List<Point>> map = new ConcurrentHashMap<>();
        DataFilter = this.data.stream().filter(point -> !finalVar.contains(point)).collect(Collectors.toList());
        DataFilter.stream().forEach(point -> {
            TreeMap<Double, Point> resMap = new TreeMap<>();
            finalVar.forEach(cluPoint -> {
                Double distance = distance(cluPoint, point, DistanceType.LAT_LON);
                resMap.put(distance, cluPoint);
            });
            Point bestClusterPoint = bestClusterPoint(resMap);
            List<Point> points = map.get(bestClusterPoint);
            if (points == null) {
                points = new ArrayList<>();
            }
            points.add(point);
            map.put(bestClusterPoint, points);
        });
        map.forEach((clusterPoint, data) -> {
            Cluster cluster = new Cluster();
            cluster.setClusterPoint(clusterPoint);
            cluster.setData(data);
            initclusters.add(cluster);
        });
        this.clusters = cloneClusterSet(initclusters);
        Double cur = distortions();
        if (cur.doubleValue() == this.distortions.doubleValue()) {
            this.iteration = false;
        }
        if (cur.doubleValue() < this.distortions.doubleValue()) {
            this.distortions = cur;
        }
//        long interval = timer.interval();
//        log.info("genClusters:{}",interval);
        return this.clusters;
    }

    /**
     * 生成新的中心点集合
     *
     * @return
     */
    private List<Point> genNewClusterPoint() {
        List<Point> points = new ArrayList<>();
        this.clusters.forEach(cluster -> {
            Double sum_pointX = cluster.getData().stream().map(point -> point == null ? 0D : point.getX()).reduce(0D, (a, b) -> a + b);
            Double avg_pointX = sum_pointX / cluster.getData().size();
            Double sum_pointY = cluster.getData().stream().map(point -> point == null ? 0D : point.getY()).reduce(0D, (a, b) -> a + b);
            Double avg_pointY = sum_pointY / cluster.getData().size();

            avg_pointX = NumberUtil.round(avg_pointX, 4).doubleValue();
            avg_pointY = NumberUtil.round(avg_pointY, 4).doubleValue();
            points.add(new Point(avg_pointX, avg_pointY));
        });
        this.clusterPoints = clonePointList(points);
        return points;
    }

    /**
     * 内部自适应
     *
     * @return
     */
    private boolean fit() {
        do {
            // 计算每个点到中心点的距离 将点划分到簇
            genClusters();
            // 计算簇中各维度的加权平均值 作为新的中心点
            genNewClusterPoint();
            //迭代加一
            this.countIteration.incrementAndGet();
        } while (iteration());
        // 重复上述步骤 直到中心点不再变化

        return true;
    }

//    @Async
    @Override
    public void kmeans(int k, List<Point> data, boolean save2db, Long dataId) {
        log.info("=================K-means start====================");
        TimeInterval timer = DateUtil.timer();
        Double min = Double.MAX_VALUE;
        //允许畸变误差范围
        final Double DELTA = 200D;
        boolean canIterator = true;
        AtomicInteger count = new AtomicInteger();
        Set<Cluster> clusters;
        List<Point> cloneData = clonePointList(data);
        while (canIterator) {
            //初始化
            init(k, cloneData, save2db, dataId);
            //开始执行内部自适应聚类
            fit();
            //暂存结果
            clusters = getClusters();
            //内部迭代次数
            int var1 = getCountIteration().intValue();
            count.incrementAndGet();
            //畸变程度
            Double distortions = getDistortions();
            if (Math.abs(distortions.doubleValue() - min.doubleValue()) <= DELTA) {
                this.clusters = clusters;
                canIterator = false;
                continue;
            }
            if (distortions.doubleValue() < min) {
                min = distortions;
                clusters = cloneClusterSet(this.clusters);
            }
            log.info("K值：{} 迭代次数：{} 畸变程度:{}", k, var1, distortions);
        }
        //将簇数据集入库
        saveCluster2db();
        saveDistortion(new Distortion(k, count.intValue(), min, dataId));
        long interval = timer.interval();
        log.info(">>>>> K值:{} 迭代次数:{} 最小畸变:{} 运行时长:{}ms <<<<<", k, count, min, interval);
        log.info("=============K-means finish=======================");
    }

    private boolean iteration() {
        return this.iteration;
    }

    @Deprecated
    private boolean checkClusterPoint(List<Point> pl1, List<Point> pl2) {
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
     * 实现了欧式距离 经纬度距离计算
     *
     * @param p1
     * @param p2
     * @return
     */
    private Double distance(Point p1, Point p2, DistanceType distanceType) {
        if (distanceType == DistanceType.LAT_LON) {
            return HaverSine.Distance(p1.getX(), p1.getY(), p2.getX(), p2.getY());
        } else if (distanceType == DistanceType.PLANE) {
            return Math.sqrt(Math.abs((p1.getX() - p2.getX()) * (p1.getX() - p2.getX()) + ((p1.getY() - p2.getY()) * (p1.getY() - p2.getY()))));
        }
        return 0D;
    }

    /**
     * 将簇数据存入数据库
     *
     * @return
     */
    @Async
    protected void saveCluster2db() {
        if (!this.save2db) {
            return;
        }
        boolean exist = clusterMapper.selectList(new QueryWrapper<Cluster>().eq("k", this.k).eq("data_id", dataId)).size() > 0 ? true : false;
        if (exist) {
            clusterMapper.delete(new QueryWrapper<Cluster>().eq("k", k).eq("data_id", dataId));
        }
        clusters.parallelStream().forEach(cluster -> {
            cluster.setK(this.k);
            cluster.setDataId(dataId);
            cluster.setX(cluster.getClusterPoint().getX());
            cluster.setY(cluster.getClusterPoint().getY());
            List<String> pointIds = cluster.getData().stream().map(Point::getId).map(var -> Long.toString(var)).collect(Collectors.toList());
            cluster.setPointId(pointIds.toString());
            clusterMapper.insert(cluster);
        });
        return;
    }

    private Double distortions() {
        this.clusters.forEach(cluster -> {
            final Double[] sum = {0D};
            cluster.getData().forEach(point -> {
                sum[0] = sum[0] + Math.pow(distance(cluster.getClusterPoint(), point, DistanceType.LAT_LON), 2);
                cluster.setDistortions(sum[0]);
            });
        });
        Set<Cluster> var1 = cloneClusterSet(this.clusters);
        Double distortions = var1.stream().map(Cluster::getDistortions).reduce(0D, Double::sum);
        return NumberUtil.round(distortions, 0).doubleValue();
    }

    @Override
    public Double getDistortions() {
        return this.distortions;
    }

    @Async
    protected void saveDistortion(Distortion distortion) {
        if (!this.save2db) {
            return;
        }
        boolean exist = distortionMapper.selectList(new QueryWrapper<Distortion>().eq("k", k).eq("data_id", dataId)).size() > 0 ? true : false;
        if (exist) {
            distortionMapper.delete(new QueryWrapper<Distortion>().eq("k", k).eq("data_id", dataId));
        }
        distortionMapper.insert(distortion);
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

    private Set<Cluster> cloneClusterSet(Set<Cluster> clusters) {
        Set<Cluster> var1 = new HashSet<Cluster>(0);
        clusters.forEach(cluster -> {
            var1.add(ObjectUtil.clone(cluster));
        });
        return var1;
    }

    private List<Point> getClusterPoints() {
        return clusterPoints;
    }

    private void setClusterPoints(List<Point> clusterPoints) {
        this.clusterPoints = clusterPoints;
    }

    private List<Point> getData() {
        return data;
    }

    private void setData(List<Point> data) {
        this.data = data;
    }

    @Override
    public Set<Cluster> getClusters() {
        return this.clusters;
    }

    @Override
    public AtomicInteger getCountIteration() {
        return countIteration;
    }

    @Override
    public Set<Cluster> getClusters(int k,Long dataId) {
        List<Cluster> clusters = clusterMapper.selectList(new QueryWrapper<Cluster>().eq("k", k).eq("data_id",dataId).groupBy("x"));
        clusters.forEach(cluster -> {
            List<Cluster> var = clusterMapper.selectList(new QueryWrapper<Cluster>().eq("x", cluster.getX()).eq("y", cluster.getY()).eq("k", k).eq("data_id",dataId));
            List<String> pointIds = Arrays.asList(var.get(0).getPointId().replace("[", "").replace("]", "").split(","));
            List<Point> points = dataService.getPointsById(pointIds);
            cluster.setData(points);
        });
        return clusters.stream().collect(Collectors.toSet());
    }

    @Override
    public List<Distortion> getKLine(Long dataId) {
        return distortionMapper.selectList(new QueryWrapper<Distortion>().orderByAsc("k").eq("data_id",dataId));
    }

    @Override
    public int bestK(Long dataId) {
        List<Distortion> kLine = getKLine(dataId);
        double min = Double.MAX_VALUE;
        int bestIndex = 0;
        List<Double> slops = new ArrayList<>();
        for (int i = 0; i < kLine.size()-1; i++) {
            double v = Math.abs(kLine.get(i).getDistortions() - kLine.get(i+1).getDistortions()) / Math.abs(kLine.get(i).getK() -kLine.get(i+1).getK());
            slops.add(v);
//            log.info("{}-{} v:{}",kLine.get(i).getK(),kLine.get(i+1).getK(),v);
            if(v<min){
                bestIndex = i;
            }
        }
        return kLine.get(bestIndex).getK();
    }


}
