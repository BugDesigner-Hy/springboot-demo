package com.haiyang.hutool.cache;/**
 * @Author: HaiYang
 * @Date: 2020/4/13 17:10
 */

import cn.hutool.cache.Cache;
import cn.hutool.cache.CacheUtil;
import cn.hutool.cache.impl.FIFOCache;
import cn.hutool.cache.impl.TimedCache;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.http.HttpStatus;

/**
 * @author: Administrator
 * @Date: 2020/4/13 17:10
 * @Description:
 */
public class CacheTest {

    public static void main(String[] args) {

        Cache<String, String> fifoCache = CacheUtil.newFIFOCache(3);
        //加入元素，每个元素可以设置其过期时长，DateUnit.SECOND.getMillis()代表每秒对应的毫秒数，在此为3秒
        fifoCache.put("key1", "value1", DateUnit.SECOND.getMillis() * 3);
        fifoCache.put("key2", "value2", DateUnit.SECOND.getMillis() * 3);
        fifoCache.put("key3", "value3", DateUnit.SECOND.getMillis() * 3);

        //由于缓存容量只有3，当加入第四个元素的时候，根据FIFO规则，最先放入的对象将被移除
        fifoCache.put("key4", "value4", DateUnit.SECOND.getMillis() * 3);

        //value1为null
        String value1 = fifoCache.get("key1");

        Cache<String, String> lfuCache = CacheUtil.newLFUCache(3);
        //通过实例化对象创建
        //LFUCache<String, String> lfuCache = new LFUCache<String, String>(3);

        lfuCache.put("key1", "value1", DateUnit.SECOND.getMillis() * 3);
        lfuCache.get("key1");//使用次数+1
        lfuCache.put("key2", "value2", DateUnit.SECOND.getMillis() * 3);
        lfuCache.put("key3", "value3", DateUnit.SECOND.getMillis() * 3);
        lfuCache.put("key4", "value4", DateUnit.SECOND.getMillis() * 3);

        //由于缓存容量只有3，当加入第四个元素的时候，根据LRU规则，最少使用的将被移除（2,3被移除）
        String value2 = lfuCache.get("key2");//null
        String value3 = lfuCache.get("key3");//null


        Cache<String, String> lruCache = CacheUtil.newLRUCache(3);
        //通过实例化对象创建
        //LRUCache<String, String> lruCache = new LRUCache<String, String>(3);
        lruCache.put("key1", "value1", DateUnit.SECOND.getMillis() * 3);
        lruCache.put("key2", "value2", DateUnit.SECOND.getMillis() * 3);
        lruCache.put("key3", "value3", DateUnit.SECOND.getMillis() * 3);
        lruCache.get("key1");//使用时间推近
        lruCache.put("key4", "value4", DateUnit.SECOND.getMillis() * 3);

        //由于缓存容量只有3，当加入第四个元素的时候，根据LRU规则，最少使用的将被移除（2被移除）
        String value222 = lruCache.get("key");//null


        //创建缓存，默认4毫秒过期
        TimedCache<String, String> timedCache = CacheUtil.newTimedCache(4);
        //实例化创建
        //TimedCache<String, String> timedCache = new TimedCache<String, String>(4);

        timedCache.put("key1", "value1", 1);//1毫秒过期
        timedCache.put("key2", "value2", DateUnit.SECOND.getMillis() * 5);
        timedCache.put("key3", "value3");//默认过期(4毫秒)

        //启动定时任务，每5毫秒秒检查一次过期
        timedCache.schedulePrune(5);

        //等待5毫秒
        ThreadUtil.sleep(5);

        //5毫秒后由于value2设置了5毫秒过期，因此只有value2被保留下来
        String value11 = timedCache.get("key1");//null
        String value22 = timedCache.get("key2");//value2

        //5毫秒后，由于设置了默认过期，key3只被保留4毫秒，因此为null
        String value33 = timedCache.get("key3");//null

        //取消定时清理
        timedCache.cancelPruneSchedule();
        //如果用户在超时前调用了get(key)方法，会重头计算起始时间。举个例子，用户设置key1的超时时间5s，
        // 用户在4s的时候调用了get("key1")，此时超时时间重新计算，再过4s调用get("key1")方法值依旧存在。
        // 如果想避开这个机制，请调用get("key1", false)方法。

    }
}
