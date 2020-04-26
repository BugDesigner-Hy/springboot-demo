package com.haiyang.spring.collection;/**
 * @Author: HaiYang
 * @Date: 2020/4/24 14:19
 */

import cn.hutool.core.lang.Console;
import com.google.common.collect.EnumBiMap;
import com.google.common.collect.HashBiMap;
import org.hibernate.validator.internal.engine.messageinterpolation.InterpolationTermType;

/**
 * @author: Administrator
 * @Date: 2020/4/24 14:19
 * @Description: 实现键值对的双向映射
 */
public class BiMap_demo {

    public static void main(String[] args) {
        HashBiMap<Object, Object> hashBiMap = HashBiMap.create();
        hashBiMap.put("haiyang", 23);
        Object age = hashBiMap.get("haiyang");
        Object name = hashBiMap.inverse().get(23);
        Console.log("name:{},age:{}", name, age);


    }
}
