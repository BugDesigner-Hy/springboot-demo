package com.haiyang.shrio.entity;/**
 * @Author: HaiYang
 * @Date: 2020/11/13 10:56
 */

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

/**
 * @author: Administrator
 * @Date: 2020/11/13 10:56
 * @Description:
 */
@Data
@AllArgsConstructor
public class User {
    private String id;
    private String userName;
    private String password;
    /**
     * 用户对应的角色集合
     */
    private Set<Role> roles;

}
