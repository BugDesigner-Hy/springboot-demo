package com.haiyang.kmeans.controller;/**
 * @Author: HaiYang
 * @Date: 2020/4/10 16:43
 */

import com.haiyang.kmeans.entity.Point;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: Administrator
 * @Date: 2020/4/10 16:43
 * @Description:
 */
public class test {
    public static void main(String[] args) {
        Point p1 = new Point(1D,2D);
        Point p2 = new Point(3D,4D);

        List<Point> a = null;
//        a.add(p1);
//        a.add(p2);
        List<Point> b = new ArrayList<>();
        b.add(p2);
        b.add(p1);

        System.out.println("a = b" + (b.containsAll(a)));

    }
}
