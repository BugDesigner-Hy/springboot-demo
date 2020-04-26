package com.haiyang.spring.basic;/**
 * @Author: HaiYang
 * @Date: 2020/4/26 9:42
 */

import cn.hutool.core.lang.Console;
import com.google.common.math.IntMath;

import java.math.RoundingMode;

/**
 * @author: Administrator
 * @Date: 2020/4/26 09:42
 * @Description:
 */
public class Math_demo {
    public static void main(String[] args) {
        int divide = IntMath.divide(21, 4, RoundingMode.HALF_DOWN);
        Console.log("5/21={}",divide);

    }
}
