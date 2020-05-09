package com.haiyang.spring.entity;/**
 * @Author: HaiYang
 * @Date: 2020/4/3 13:54
 */

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author: Administrator
 * @Date: 2020/4/3 13:54
 * @Description:
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @TableId(type = IdType.AUTO)
    private int id;
    private String name;
    private String age;
    private String email;

}
