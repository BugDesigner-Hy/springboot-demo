package com.haiyang.spring.basic;/**
 * @Author: HaiYang
 * @Date: 2020/4/24 11:14
 */

import static cn.hutool.core.lang.Console.*;

import com.google.common.base.Objects;



/**
 * @author: Administrator
 * @Date: 2020/4/24 11:14
 * @Description:
 */
public class Objects_demo {
    public static void main(String[] args) {

        Objects.equal(null,"a");//false
        int code = Objects.hashCode("aaa");
        log(code);
    }
}
