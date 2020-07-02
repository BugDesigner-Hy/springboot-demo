package com.haiyang.spring.config;/**
 * @Author: HaiYang
 * @Date: 2020/6/4 15:39
 */

import com.haiyang.spring.bean.Student;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: Administrator
 * @Date: 2020/6/4 15:39
 * @Description:
 */
@Configuration
public class BaseConfig {

    @Bean
    public Student student(){
        return new Student(1,"haiyang");
    }
}
