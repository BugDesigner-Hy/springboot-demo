package com.haiyang.spring.collection;/**
 * @Author: HaiYang
 * @Date: 2020/4/24 14:02
 */

import static cn.hutool.core.lang.Console.*;

import com.google.common.collect.*;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 * @author: Administrator
 * @Date: 2020/4/24 14:02
 * @Description: 键映射到任意多个值 解决Map<K, List<V>>或Map<K, Set<V>>这种结构
 */
public class Multimap_demo {

    public static void main(String[] args) {
        ArrayListMultimap<Object, Object> arrayListMultimap = ArrayListMultimap.create();
        arrayListMultimap.put("a",1);
        arrayListMultimap.put("a",2);
        arrayListMultimap.put("a",3);
        arrayListMultimap.put("b",1);
        arrayListMultimap.put("b",2);
        Map<Object, Collection<Object>> asMap = arrayListMultimap.asMap();
        log("asMap:{}",asMap);
        boolean containsEntry = arrayListMultimap.containsEntry("a", 3);
        log("containsEntry(a,3):{}",containsEntry);
        Collection<Map.Entry<Object, Object>> entries = arrayListMultimap.entries();
        log("entries:{}",entries);
        arrayListMultimap.forEach((key,value)->{
            log("key:{},value:{}",key,value);
        });
        Multiset<Object> keys = arrayListMultimap.keys();
        log("keys:{}",keys);
        Set<Object> keySet = arrayListMultimap.keySet();
        log("keyset:{}",keySet);
        Collection<Object> values = arrayListMultimap.values();
        log("values:{}",values);
        int size = arrayListMultimap.size();
        log("size:{}",size);

        ArrayListMultimap<Object, Object> inverse  =Multimaps.invertFrom(arrayListMultimap, ArrayListMultimap.create());
        log("inverse:{}",inverse);


    }
}
