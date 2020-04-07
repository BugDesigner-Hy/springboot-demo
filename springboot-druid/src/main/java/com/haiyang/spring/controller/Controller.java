package com.haiyang.spring.controller;/**
 * @Author: HaiYang
 * @Date: 2020/4/7 14:50
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author: Administrator
 * @Date: 2020/4/7 14:50
 * @Description:
 */
@RestController
public class Controller {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @GetMapping("/test")
    public void testDruid(){
        Map users = jdbcTemplate.queryForMap("select * from sys_role");
        users.forEach((k,v) -> System.out.println(k+":"+v));
    }
}
