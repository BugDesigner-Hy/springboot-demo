package com.haiyang.spring.controller;/**
 * @Author: HaiYang
 * @Date: 2020/5/20 15:39
 */

import com.haiyang.spring.config.UserConfig;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author: Administrator
 * @Date: 2020/5/20 15:39
 * @Description:
 */
@RestController
public class UserController {

    @Resource
    UserConfig user;

    @GetMapping("user")
    public String getUserInfo(){
        return "user.name:"+user.getName()+"&"+"user.age:"+user.getAge()+"&user.id:"+user.getId();
    }
}
