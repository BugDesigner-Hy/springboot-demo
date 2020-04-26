package com.haiyang.spring.collection;/**
 * @Author: HaiYang
 * @Date: 2020/4/24 11:30
 */

import cn.hutool.core.lang.Console;
import com.google.common.collect.ImmutableSet;

/**
 * @author: Administrator
 * @Date: 2020/4/24 11:30
 * @Description: 不可变集合
 */
public class ImmutableSet_demo {

    public static final ImmutableSet COLOR = ImmutableSet.of(
            "red","orange","blue"
    );

    public static void main(String[] args) {
        String color_red = COLOR.asList().get(0).toString();
        Console.log(color_red);
    }
}
