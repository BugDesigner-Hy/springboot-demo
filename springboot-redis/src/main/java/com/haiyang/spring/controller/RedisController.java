package com.haiyang.spring.controller;/**
 * @Author: HaiYang
 * @Date: 2020/5/11 15:08
 */

import com.haiyang.spring.entity.User;
import com.haiyang.spring.util.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author: Administrator
 * @Date: 2020/5/11 15:08
 * @Description:
 */
@Slf4j
@RequestMapping("/redis")
@RestController
public class RedisController {

    // redis中存储的过期时间60s
    private static int ExpireTime = 30;

    @Resource
    private RedisUtil redisUtil;

    @RequestMapping("set")
    public boolean redisSet(String key, String value) {
        User userEntity = new User();
        userEntity.setId(1L);
        userEntity.setName("haiyang");
        userEntity.setAge(26);
        userEntity.setCreateTime(new Date());

        return redisUtil.set(key, userEntity, ExpireTime);
//        return redisUtil.set(key,value);
    }

    @RequestMapping("get")
    public Object redisGet(String key) {
        return redisUtil.get(key);
    }

    @RequestMapping("expire")
    public boolean expire(String key) {
        return redisUtil.expire(key, ExpireTime);
    }


}
