package com.haiyang.dynamic;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.haiyang.dynamic.mapper")
@SpringBootApplication
public class SpringbootDynamicDatasourceDruidApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootDynamicDatasourceDruidApplication.class, args);
    }

}
