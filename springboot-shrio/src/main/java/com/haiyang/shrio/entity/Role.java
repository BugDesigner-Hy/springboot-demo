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
public class Role {

    private String id;
    private String roleName;
    /**
     * 角色对应权限集合
     */
    private Set<Permission> permissions;

}
