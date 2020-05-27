package com.haiyang.spring.pojo;/**
 * @Author: HaiYang
 * @Date: 2020/5/21 16:53
 */

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

/**
 * @author: Administrator
 * @Date: 2020/5/21 16:53
 * @Description:
 */
@Data
@AllArgsConstructor
public class Message {
    private int id;
    private String content;
    private Date date;
}
