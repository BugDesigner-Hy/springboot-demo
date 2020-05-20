package com.haiyang.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@EnableCaching  // 启用缓存
@SpringBootApplication
public class SpringbootSessionApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootSessionApplication.class, args);
    }

}
