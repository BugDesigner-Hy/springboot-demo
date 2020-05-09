package com.haiyang.spring.jobparameters;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableBatchProcessing
public class JobParamApplication {

    public static void main(String[] args) {
        SpringApplication.run(JobParamApplication.class, args);
    }

}
