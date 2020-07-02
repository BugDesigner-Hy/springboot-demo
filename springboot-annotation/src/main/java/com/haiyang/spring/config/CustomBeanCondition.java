package com.haiyang.spring.config;/**
 * @Author: HaiYang
 * @Date: 2020/6/18 9:43
 */

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * @author: Administrator
 * @Date: 2020/6/18 09:43
 * @Description:
 */
public class CustomBeanCondition implements Condition {
    @Override
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
        // 获取到ioc使用的beanfactory
        ConfigurableListableBeanFactory beanFactory = conditionContext.getBeanFactory();
        // 获取加载器
        ClassLoader classLoader = conditionContext.getClassLoader();
        //获取当前环境信息
        Environment environment = conditionContext.getEnvironment();
        //获取bean定义
        BeanDefinitionRegistry registry = conditionContext.getRegistry();

        String os = environment.getProperty("os.name");
        if(os.contains("linux")){
            return true;
        }
        return false;
    }
}
