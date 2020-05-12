package com.haiyang.spring.entity;/**
 * @Author: HaiYang
 * @Date: 2020/5/11 15:19
 */

import lombok.Data;

import java.util.Date;

/**
 * @author: Administrator
 * @Date: 2020/5/11 15:19
 * @Description:
 */
@Data
public class User {

    private Long id;
    private String name;
    private int age;
    private Date createTime;

}
