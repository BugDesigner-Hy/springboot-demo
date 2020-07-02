package com.haiyang.spring.entity;/**
 * @Author: HaiYang
 * @Date: 2020/6/18 14:30
 */

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @author: Administrator
 * @Date: 2020/6/18 14:30
 * @Description:
 */
@Component
public class Apple {

    public Apple() {
        System.out.println("Apple constructor...  ");
    }

    @PostConstruct
    public void init(){
        System.out.println("Apple init.... " );
    }

    @PreDestroy
    public void destroy(){
        System.out.println("Apple destroy.... " );
    }
}
