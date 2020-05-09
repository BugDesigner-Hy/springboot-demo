package com.haiyang.spring.output.multiple;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableBatchProcessing
public class MultipleApplication {

    public static void main(String[] args) {
        SpringApplication.run(MultipleApplication.class, args);
    }

}
