package com.haiyang.spring.controller;/**
 * @Author: HaiYang
 * @Date: 2020/4/3 10:31
 */

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: Administrator
 * @Date: 2020/4/3 10:31
 * @Description:
 */
@RestController
public class helloController {

    @GetMapping("/hello")
    public String helloWorld(){
        return "hello springboot";
    }
}
