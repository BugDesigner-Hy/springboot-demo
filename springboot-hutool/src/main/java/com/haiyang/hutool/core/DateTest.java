package com.haiyang.hutool.core;/**
 * @Author: HaiYang
 * @Date: 2020/4/13 13:54
 */

import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.TimeInterval;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;

/**
 * @author: Administrator
 * @Date: 2020/4/13 13:54
 * @Description:
 */
public class DateTest {

    public static void main(String[] args) throws InterruptedException {
        //计时器 计算某段代码或过程花费的时间
        TimeInterval timer = DateUtil.timer();
        //-----------------
        Thread.sleep(500);
        //-----------
        long interval = timer.interval();
        System.out.println("interval = " + interval);

        LocalDate localDate = LocalDate.now();

        System.out.println("localDate = " + localDate);

        System.out.println("localDate.toString() = " + localDate.toString());

        System.out.println("var1.plusDays(2) = " + localDate.plusDays(2));

        System.out.println("localDate.plus(2, ChronoUnit.DAYS) = " + localDate.plus(2, ChronoUnit.DAYS));

        System.out.println("localDate.plus(Period.ofDays(2)) = " + localDate.plus(Period.ofDays(2)));

        System.out.println("localDate.isLeapYear() = " + localDate.isLeapYear());

        System.out.println("localDate.adjustInto(localDate) = " + localDate.adjustInto(localDate));

        System.out.println("localDate.atStartOfDay() = " + localDate.atStartOfDay());

        System.out.println("localDate.atStartOfDay(ZoneId.of(\"Asia/Shanghai\")) = " + localDate.atStartOfDay(ZoneId.of("Asia/Shanghai")));

        System.out.println("localDate.atTime(LocalTime.now()) = " + localDate.atTime(LocalTime.now()));

        System.out.println("localDate.atTime(OffsetTime.now()) = " + localDate.atTime(OffsetTime.now()));

        System.out.println("localDate.atTime(12,59,59) = " + localDate.atTime(12, 59, 59));

        System.out.println("localDate.compareTo(LocalDate.now()) = " + localDate.compareTo(LocalDate.of(2022,3,15)));

        System.out.println("localDate.format(DateTimeFormatter.ofPattern(\"yyyyMMdd\")) = " + localDate.format(DateTimeFormatter.ofPattern("yyyyMMdd")));

        System.out.println("localDate.get(ChronoField.DAY_OF_WEEK) = " + localDate.get(ChronoField.DAY_OF_WEEK));

        System.out.println("localDate.get(ChronoField.ALIGNED_DAY_OF_WEEK_IN_MONTH) = " + localDate.get(ChronoField.ALIGNED_DAY_OF_WEEK_IN_MONTH));

        System.out.println("localDate.getDayOfMonth() = " + localDate.getDayOfMonth());

        System.out.println("localDate.getDayOfYear() = " + localDate.getDayOfYear());

        System.out.println("localDate.getDayOfWeek() = " + localDate.getDayOfWeek());

        System.out.println("localDate.getEra() = " + localDate.getEra());

        assert localDate.isAfter(LocalDate.of(1993, 11, 29));

        assert localDate.isBefore(LocalDate.of(1993, 11, 29));

        System.out.println("localDate.minusDays(2) = " + localDate.minusDays(2));

        //获取指定字段的有效值范围
        System.out.println("localDate.range(ChronoField.DAY_OF_WEEK) = " + localDate.range(ChronoField.DAY_OF_WEEK)); //1-7

        System.out.println("localDate.range(ChronoField.ALIGNED_WEEK_OF_MONTH) = " + localDate.range(ChronoField.ALIGNED_WEEK_OF_MONTH)); //1-5

        System.out.println("localDate.lengthOfMonth() = " + localDate.lengthOfMonth());

        //获取两个日期的期间-Period
        System.out.println("localDate.until(LocalDate.of(2020,2,22)) = " + localDate.until(LocalDate.of(2020, 2, 22)).getDays());

        LocalDateTime localDateTime = LocalDateTime.parse("2020-03-19 17:40:23",DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        System.out.println("localDateTime = " + localDateTime);

        System.out.println("localDateTime.format(DateTimeFormatter.ofPattern(\"yyyy/MM/dd HH:mm:ss\")) = " + localDateTime.format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")));


    }
}
