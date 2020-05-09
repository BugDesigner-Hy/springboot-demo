package com.haiyang.spring.jobflow2;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableBatchProcessing
public class JobFlow2Application {

    public static void main(String[] args) {
        SpringApplication.run(JobFlow2Application.class, args);
    }

}
