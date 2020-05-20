package com.haiyang.spring.config;/**
 * @Author: HaiYang
 * @Date: 2020/5/20 15:32
 */

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @author: Administrator
 * @Date: 2020/5/20 15:32
 * @Description:
 */
@Data
@Component
@ConfigurationProperties(prefix = "user")
@PropertySource(value = {"classpath:user.properties"},ignoreResourceNotFound = false,encoding = "UTF-8",name = "user.properties")
public class UserConfig {

    private String id;

    private String name;

    private int age;

}
