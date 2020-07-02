package com.haiyang.spring.config;/**
 * @Author: HaiYang
 * @Date: 2020/6/18 11:45
 */

import com.haiyang.spring.entity.Cat;
import org.springframework.beans.factory.FactoryBean;

/**
 * @author: Administrator
 * @Date: 2020/6/18 11:45
 * @Description:
 */
public class CatFactoryBean implements FactoryBean<Cat> {
    @Override
    public Cat getObject() throws Exception {
        return new Cat();
    }

    @Override
    public Class<?> getObjectType() {
        return Cat.class;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }
}
