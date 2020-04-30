package com.haiyang.spring.server;/**
 * @Author: HaiYang
 * @Date: 2020/4/27 10:13
 */

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author: Administrator
 * @Date: 2020/4/27 10:13
 * @Description:
 */
@Component
public class LikeRedisTemplate {
    private Map<Object,Object> RedisMap = new ConcurrentHashMap<>();

    public void save(Object id,Object name){
        RedisMap.put(id,name);
    }

    public void delete(Object id){
        RedisMap.remove(id);
    }

    public Object get(Object id){
        return RedisMap.get(id);
    }
}
