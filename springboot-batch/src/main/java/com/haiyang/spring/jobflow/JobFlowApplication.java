package com.haiyang.spring.jobflow;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableBatchProcessing
public class JobFlowApplication {

    public static void main(String[] args) {
        SpringApplication.run(JobFlowApplication.class, args);
    }

}
