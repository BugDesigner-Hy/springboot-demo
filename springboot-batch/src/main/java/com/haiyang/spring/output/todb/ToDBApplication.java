package com.haiyang.spring.output.todb;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableBatchProcessing
public class ToDBApplication {

    public static void main(String[] args) {
        SpringApplication.run(ToDBApplication.class, args);
    }

}
