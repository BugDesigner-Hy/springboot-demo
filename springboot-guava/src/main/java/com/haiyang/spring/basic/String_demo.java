package com.haiyang.spring.basic;/**
 * @Author: HaiYang
 * @Date: 2020/4/24 16:50
 */

import cn.hutool.core.lang.Console;
import com.google.common.base.CharMatcher;
import com.google.common.base.Charsets;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.primitives.*;

import java.util.List;

/**
 * @author: Administrator
 * @Date: 2020/4/24 16:50
 * @Description:
 */
public class String_demo {
    public static void main(String[] args) {
        String res = Joiner.on("-").skipNulls().join("a","b",null,"c");
        //这样做字符处理  Charsets
        res.getBytes(Charsets.UTF_8);
        Console.log(res);

        List<String> list = Splitter.on("-").splitToList(res);
        Console.log(list);

        Ints.asList(1,2,3);
        int divide = UnsignedInts.divide(2, 3);
        Console.log("2/3={}",divide);

    }
}
