package com.haiyang.spring.basic;/**
 * @Author: HaiYang
 * @Date: 2020/4/24 10:50
 */

import cn.hutool.core.lang.Console;

import java.util.Arrays;
import java.util.List;

import static com.google.common.base.Preconditions.*;

/**
 * @author: Administrator
 * @Date: 2020/4/24 10:50
 * @Description: 前置条件：让方法调用的前置条件判断更简单
 * 推荐静态导入 import static com.google.common.base.Preconditions.*;
 */
public class Preconditions_demo {
    public static void main(String[] args) {
        int arg = 5;
        checkArgument(arg==10,"except %s but %s",10,arg);

        Integer a = 5;
        //checkNotNull直接返回检查的参数
        //让你可以在构造函数中保持字段的单行赋值风格：this.field = checkNotNull(field)
        a = checkNotNull(a, "arg is null");
        Console.log(a);

        List<String> list = Arrays.asList("a","b","c","d");
        checkElementIndex(5,list.size());

    }
}
