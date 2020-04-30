package com.haiyang.kmeans.service.impl;/**
 * @Author: HaiYang
 * @Date: 2020/4/17 11:35
 */

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.haiyang.kmeans.entity.Point;
import com.haiyang.kmeans.entity.YyOrder;
import com.haiyang.kmeans.mapper.YyOrderMapper;
import com.haiyang.kmeans.service.DataService;
import com.haiyang.kmeans.entity.Period;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;

/**
 * @author: Administrator
 * @Date: 2020/4/17 11:35
 * @Description:
 */
@Service
@DS("slave")
public class DataServiceImpl implements DataService {

    @Resource
    YyOrderMapper yyOrderMapper;

    @Override
    public List<Point> getPointsByCityAndDateAndPeriod(String cityCode, String dateStr, Period period) {
        Map<String,Long> dateQuery = getTimestamps(dateStr, period);
        QueryWrapper<YyOrder> wrapper = new QueryWrapper<YyOrder>().select("order_id,slon,slat,id").eq("city_code", cityCode).between("timestamp", dateQuery.get("begin"), dateQuery.get("end")).groupBy("order_id,slon,slat");
        List<YyOrder> orders = yyOrderMapper.selectList(wrapper);
        List<Point> pointsUnsafe = new ArrayList<>();
        List<Point> pointsSafe = Collections.synchronizedList(pointsUnsafe);
        orders.parallelStream().forEach(order -> {
            Point point = new Point();
            point.setX(order.getSlon());
            point.setY(order.getSlat());
            point.setId(order.getId());
            pointsSafe.add(point);
        });
        return pointsSafe;
    }

    private Map<String,Long> getTimestamps(String dateStr, Period period) {
        HashMap<String,Long> map = new HashMap<>(2);
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
//        LocalDateTime date = LocalDate.parse(dateStr, formatter).atStartOfDay();
//        LocalDateTime beginDate = date.plus(period.getBegin(), ChronoUnit.MILLIS);
//        LocalDateTime endDate = date.plus(period.getEnd(), ChronoUnit.MILLIS);
//        map.put("begin",beginDate.toInstant(ZoneOffset.of("+8")).toEpochMilli());
//        map.put("end",endDate.toInstant(ZoneOffset.of("+8")).toEpochMilli());
        map.put("begin",Long.valueOf(1586620800000L) );
        map.put("end",Long.valueOf(1587830399000L) );
        return map;
    }

    @Override
    public Long genDataId(String cityCode, String dateStr, Period period) {
        StringBuilder dataIdBuilder = new StringBuilder();
        dataIdBuilder.append(cityCode).append(dateStr).append(period.getTag());
        return Long.parseLong(dataIdBuilder.toString());
    }

    @Override
    public List<Point> getPointsById(List<String> idList){
        List<YyOrder> orders = yyOrderMapper.selectBatchIds(idList);
        List<Point> pointsUnsafe = new ArrayList<>();
        List<Point> pointsSafe = Collections.synchronizedList(pointsUnsafe);
        orders.parallelStream().forEach(order -> {
            Point point = new Point();
            point.setX(order.getSlon());
            point.setY(order.getSlat());
            point.setId(order.getId());
            pointsSafe.add(point);
        });
        return pointsSafe;
    }
}
