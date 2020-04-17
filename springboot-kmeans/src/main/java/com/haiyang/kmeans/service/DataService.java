package com.haiyang.kmeans.service;

import com.haiyang.kmeans.entity.Point;
import com.haiyang.kmeans.util.Period;

import java.util.List;

/**
 * @Author: HaiYang
 * @Date: 2020/4/17 11:35
 */
public interface DataService {

    List<Point> getPointsByCityAndDateAndPeriod(String cityCode, String date, Period period);

    Long genDataId(String cityCode,String dateStr,Period period);

    List<Point> getPointsById(List<String> idList);
}
