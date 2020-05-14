package com.haiyang.kmeans.entity;/**
 * @Author: HaiYang
 * @Date: 2020/5/14 10:57
 */

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author: Administrator
 * @Date: 2020/5/14 10:57
 * @Description:
 */
@Data
@AllArgsConstructor
public class SimpleDriver {

    String driverNumber;
    /**
     * 起始点经度
     */
    private Double slon;

    /**
     * 起始点纬度
     */
    private Double slat;

    private Double distance;

    public SimpleDriver(String driverNumber, Double distance) {
        this.driverNumber = driverNumber;
        this.distance = distance;
    }
}
