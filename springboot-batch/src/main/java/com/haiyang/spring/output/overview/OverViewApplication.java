package com.haiyang.spring.output.overview;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableBatchProcessing
public class OverViewApplication {

    public static void main(String[] args) {
        SpringApplication.run(OverViewApplication.class, args);
    }

}
