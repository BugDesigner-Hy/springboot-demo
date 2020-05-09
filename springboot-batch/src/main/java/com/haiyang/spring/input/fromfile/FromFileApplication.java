package com.haiyang.spring.input.fromfile;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableBatchProcessing
public class FromFileApplication {

    public static void main(String[] args) {
        SpringApplication.run(FromFileApplication.class, args);
    }

}
