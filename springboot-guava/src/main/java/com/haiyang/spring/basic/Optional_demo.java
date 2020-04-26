package com.haiyang.spring.basic;/**
 * @Author: HaiYang
 * @Date: 2020/4/24 10:26
 */

import cn.hutool.core.lang.Console;

import java.util.Optional;

/**
 * @author: Administrator
 * @Date: 2020/4/24 10:26
 * @Description:   使用和避免null
 */
public class Optional_demo {

    public static void main(String[] args) {
        Optional<Integer> possible = Optional.of(5);
        Optional<Integer> isnull = Optional.ofNullable(null);
        possible.isPresent();//true
        possible.get();//5
        System.out.println("possible.orElse(10) = " + isnull.orElse(10));

    }
}
