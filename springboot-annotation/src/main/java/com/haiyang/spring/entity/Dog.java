package com.haiyang.spring.entity;/**
 * @Author: HaiYang
 * @Date: 2020/6/18 14:18
 */

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

/**
 * @author: Administrator
 * @Date: 2020/6/18 14:18
 * @Description:
 */
@Component
public class Dog implements InitializingBean, DisposableBean {

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("Dog afterPropertiesSet...." );
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("Dog destroy" );
    }
}
