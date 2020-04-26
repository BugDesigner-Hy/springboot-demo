package com.haiyang.spring;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.haiyang.spring.mapper")
public class SpringbootNettyChatApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootNettyChatApplication.class, args);
    }

}
