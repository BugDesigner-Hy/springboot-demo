package com.haiyang.spring.errorhandler;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableBatchProcessing
public class ErrorHandlerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ErrorHandlerApplication.class, args);
    }

}
