package com.haiyang.spring.collection;/**
 * @Author: HaiYang
 * @Date: 2020/4/24 11:45
 */

import static cn.hutool.core.lang.Console.*;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;
import com.google.common.collect.Multisets;
import com.google.common.collect.TreeMultiset;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

/**
 * @author: Administrator
 * @Date: 2020/4/24 11:45
 * @Description:
 */
public class Multiset_demo {

    public static void main(String[] args) {
        List<String> data = Arrays.asList("a","b","c","a","a","b");
        HashMultiset<String> multiset = HashMultiset.create(data);
        int a_count = multiset.count("a");
        log("count a :{}",a_count);
        Set<String> elementSet = multiset.elementSet();
        log("Multiset中不重复元素的集合 elementSet:{}",elementSet);//[a, b, c]
        Set<Multiset.Entry<String>> entrySet = multiset.entrySet();//[a x 3, b x 2, c]
        entrySet.forEach((entry)->{
            log("element:{},count:{}",entry.getElement(),entry.getCount());
        });

        log("entrySet:{}",entrySet);
        int size = multiset.size();//6
        log("size:{}",size);



        TreeMultiset<String> treeMultiset = TreeMultiset.create(data);
        int b_count = treeMultiset.count("b");
        log("count b :{}",b_count);

    }
}
