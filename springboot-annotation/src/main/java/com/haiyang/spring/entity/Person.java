package com.haiyang.spring.entity;/**
 * @Author: HaiYang
 * @Date: 2020/6/12 17:10
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;

/**
 * @author: Administrator
 * @Date: 2020/6/12 17:10
 * @Description:
 */
@Data
@AllArgsConstructor
public class Person {

    private String name;

    private int age;

}
