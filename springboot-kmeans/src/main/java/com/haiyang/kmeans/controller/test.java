package com.haiyang.kmeans.controller;/**
 * @Author: HaiYang
 * @Date: 2020/4/10 16:43
 */

import com.haiyang.kmeans.entity.Point;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: Administrator
 * @Date: 2020/4/10 16:43
 * @Description:
 */
public class test {
    public static void main(String[] args) {
        LocalDate localDate = LocalDate.parse("20200415", DateTimeFormatter.ofPattern("yyyyMMdd"));
        System.out.println("localDate.getDayOfMonth() = " + localDate.lengthOfMonth());
        System.out.println("localDate.getMonthValue() = " + localDate.getMonthValue());
        System.out.println("localDate.getDayOfWeek().getValue() = " + localDate.getDayOfWeek().getValue());

    }
}
