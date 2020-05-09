package com.haiyang.spring.schedule;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableBatchProcessing
public class JobLaunchApplication {

    public static void main(String[] args) {
        SpringApplication.run(JobLaunchApplication.class, args);
    }

}
