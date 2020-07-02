package com.haiyang.spring.entity;/**
 * @Author: HaiYang
 * @Date: 2020/6/18 11:59
 */

/**
 * @author: Administrator
 * @Date: 2020/6/18 11:59
 * @Description:
 */
public class Car {
    public Car() {
        System.out.println("car constructor... ");
    }

    public void init(){
        System.out.println("car init...");
    }

    public void destroy(){
        System.out.println("car destroy...");
    }
}
