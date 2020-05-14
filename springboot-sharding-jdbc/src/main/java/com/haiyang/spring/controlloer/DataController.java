package com.haiyang.spring.controlloer;/**
 * @Author: HaiYang
 * @Date: 2020/5/13 15:55
 */

import com.haiyang.spring.entity.User;
import com.haiyang.spring.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author: Administrator
 * @Date: 2020/5/13 15:55
 * @Description:
 */
@Slf4j
@RestController
public class DataController {

    @Resource
    UserMapper userMapper;

    @GetMapping("/add")
    public void addUser(){
        for (int i = 1; i < 20; i++) {
            User user = new User(i,"name"+i,10+i+1,"email"+i);
            userMapper.insert(user);
        }
        log.info("done");
    }
}
