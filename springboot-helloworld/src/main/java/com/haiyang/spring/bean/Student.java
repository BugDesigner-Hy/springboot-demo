package com.haiyang.spring.bean;/**
 * @Author: HaiYang
 * @Date: 2020/6/4 15:38
 */

import org.springframework.context.annotation.Bean;

import javax.annotation.PreDestroy;

/**
 * @author: Administrator
 * @Date: 2020/6/4 15:38
 * @Description:
 */
public class Student {

    private int id;

    private String name;

    public Student(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
