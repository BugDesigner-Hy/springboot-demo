package com.haiyang.spring.controller;/**
 * @Author: HaiYang
 * @Date: 2020/5/20 14:38
 */

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: Administrator
 * @Date: 2020/5/20 14:38
 * @Description:
 */
@Slf4j
@RestController
public class DataController {

    @GetMapping("hello")
    public void hello(){
        log.info("hello world123456");
    }
}
