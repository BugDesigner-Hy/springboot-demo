package com.haiyang.spring.config;/**
 * @Author: HaiYang
 * @Date: 2020/6/18 11:32
 */

import com.haiyang.spring.entity.Water;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author: Administrator
 * @Date: 2020/6/18 11:32
 * @Description:
 */
public class MyImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {
    /**
     * 
     * @param importingClassMetadata  当前类的注解信息
     * @param registry 注册类
     */
    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        //指定bean定义
        RootBeanDefinition waterBeanDefinition = new RootBeanDefinition(Water.class);
        //注册bean到容器
        registry.registerBeanDefinition("water",waterBeanDefinition);
    }
}
