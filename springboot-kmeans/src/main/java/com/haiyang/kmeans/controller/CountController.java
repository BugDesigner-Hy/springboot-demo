package com.haiyang.kmeans.controller;/**
 * @Author: HaiYang
 * @Date: 2020/5/14 10:49
 */

import com.haiyang.kmeans.entity.Point;
import com.haiyang.kmeans.entity.SimpleDriver;
import com.haiyang.kmeans.entity.YyOrder;
import com.haiyang.kmeans.mapper.SimpleDriverMapper;
import com.haiyang.kmeans.mapper.YyOrderMapper;
import com.haiyang.kmeans.util.HaverSine;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author: Administrator
 * @Date: 2020/5/14 10:49
 * @Description:
 */
@Slf4j
@RestController
public class CountController {

    @Resource
    YyOrderMapper mapper;

    @Resource
    SimpleDriverMapper simpleDriverMapper;

    @GetMapping("query")
    public void query(){
        List<YyOrder> drivers = mapper.getchengdu();
        log.info("driver.size:{}",drivers.size());
        Point center =  new Point(104.07,30.67);
        for (YyOrder driver:drivers) {
           Double distance =  HaverSine.Distance(driver.getSlon(),driver.getSlat(),center.getX(),center.getY());
            SimpleDriver simpleDriver = new SimpleDriver(driver.getDriverNumber(), distance);
            simpleDriverMapper.insert(simpleDriver);
        }
        log.info("done");
    }
}
