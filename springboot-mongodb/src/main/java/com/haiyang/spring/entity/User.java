package com.haiyang.spring.entity;/**
 * @Author: HaiYang
 * @Date: 2020/5/12 11:17
 */

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.Date;

/**
 * @author: Administrator
 * @Date: 2020/5/12 11:17
 * @Description:
 */
@Data
@Builder
@AllArgsConstructor
@Document
public class User {

    @MongoId
    private Long id;
    private String name;
    private int age;
    private Date createTime;

}