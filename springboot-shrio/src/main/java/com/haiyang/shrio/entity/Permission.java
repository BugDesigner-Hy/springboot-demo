package com.haiyang.shrio.entity;/**
 * @Author: HaiYang
 * @Date: 2020/11/13 10:57
 */

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author: Administrator
 * @Date: 2020/11/13 10:57
 * @Description:
 */
@Data
@AllArgsConstructor
public class Permission {

    private String id;
    private String permissionsName;
}
