package com.haiyang.kmeans.controller;/**
 * @Author: HaiYang
 * @Date: 2020/4/10 14:37
 */

import com.haiyang.kmeans.entity.Cluster;
import com.haiyang.kmeans.entity.Distortion;
import com.haiyang.kmeans.entity.Point;
import com.haiyang.kmeans.service.DataService;
import com.haiyang.kmeans.service.KmeansService;
import com.haiyang.kmeans.entity.Period;
import com.haiyang.kmeans.entity.R;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

/**
 * @author: Administrator
 * @Date: 2020/4/10 14:37
 * @Description:
 */
@Slf4j
@CrossOrigin("*")
@Api(value = "K-Means Api", tags = "K-Means Api")
@RestController
public class DataController {

    @Autowired
    KmeansService kmeansService;

    @Resource
    DataService dataService;

    @PostMapping("/clusters")
    public R getClusters(@RequestParam int k,
                         @RequestParam String cityCode,
                         @RequestParam String dateStr,
                         @RequestParam Period period) {
        Long dataId = dataService.genDataId(cityCode, dateStr, period);
        Set<Cluster> clusters = kmeansService.getClusters(k, dataId);
        return R.ok().put("status", 20000).put("data", clusters);
    }

    @PostMapping("/k-line")
    public R getKLine(@RequestParam String cityCode,
                      @RequestParam String dateStr,
                      @RequestParam Period period) {
        Long dataId = dataService.genDataId(cityCode, dateStr, period);
        List<Distortion> res = kmeansService.getKLine(dataId);
        return R.ok().put("status", 20000).put("data", res);
    }

    @PostMapping("/kmeans/best-k")
    public R bestK(
            @RequestParam String cityCode,
            @RequestParam String dateStr,
            @RequestParam Period period
    ) {
        Long dataId = dataService.genDataId(cityCode, dateStr, period);
        int bestK = kmeansService.bestK(dataId);
        return R.ok().put("status", 20000).put("data", bestK);
    }

    @Async
    @PostMapping("/kmeans/city")
    public R kmeans(@RequestParam int k, @RequestParam String cityCode, @RequestParam String dateStr, @RequestParam Period period) {
        List<Point> data = dataService.getPointsByCityAndDateAndPeriod(cityCode, dateStr, period);
        Long dataId = dataService.genDataId(cityCode, dateStr, period);
        for (int i = 2; i <= k; i++) {
            kmeansService.kmeans(i, data, true, dataId);
        }
        return R.ok().put("status", 20000);
    }
}
