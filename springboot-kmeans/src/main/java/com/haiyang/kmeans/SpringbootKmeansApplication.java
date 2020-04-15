package com.haiyang.kmeans;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
@MapperScan("com.haiyang.kmeans.mapper")
public class SpringbootKmeansApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootKmeansApplication.class, args);
    }

}
