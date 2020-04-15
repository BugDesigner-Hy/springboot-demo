package com.haiyang.kmeans.controller;/**
 * @Author: HaiYang
 * @Date: 2020/4/10 14:37
 */

import cn.hutool.core.util.NumberUtil;
import com.haiyang.kmeans.entity.Cluster;
import com.haiyang.kmeans.entity.Point;
import com.haiyang.kmeans.mapper.PointMapper;
import com.haiyang.kmeans.service.KmeansService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.math.BigDecimal;
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

    @Resource
    PointMapper pointMapper;

    @GetMapping("/kmeans/{k}")
    public Boolean kmeans(@PathVariable("k") int k) {

        List<Point> data = pointMapper.selectList(null);
        kmeansService.kmeans(k, data, false);
        return Boolean.TRUE;
    }

    @GetMapping("/clusters")
    public Set<Cluster> getClusters(){
        return kmeansService.getClusters();
    }
}
