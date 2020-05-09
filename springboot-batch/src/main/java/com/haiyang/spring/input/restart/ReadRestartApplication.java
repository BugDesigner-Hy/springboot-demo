package com.haiyang.spring.input.restart;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableBatchProcessing
public class ReadRestartApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReadRestartApplication.class, args);
    }

}
