package com.haiyang.hutool.core;/**
 * @Author: HaiYang
 * @Date: 2020/4/13 12:51
 */

import cn.hutool.core.convert.Convert;

import java.util.concurrent.TimeUnit;

/**
 * @author: Administrator
 * @Date: 2020/4/13 12:51
 * @Description:
 */
public class ConvertTest {

    public static void main(String[] args) {
        String listStr = "a,b,c,d";
        System.out.println("Convert.toList(listStr) = " + Convert.toList(listStr));

        long mills = 1586746800000L;
        long mins = Convert.convertTime(mills, TimeUnit.MILLISECONDS, TimeUnit.MINUTES);
        System.out.println("mins = " + mins);

        double money = 6547.123;
        String toChinese = Convert.digitToChinese(money);
        System.out.println("toChinese = " + toChinese);


    }
}
