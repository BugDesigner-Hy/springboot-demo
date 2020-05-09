package com.haiyang.spring.input.readmultiplefile;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableBatchProcessing
public class ReadMultipleFileApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReadMultipleFileApplication.class, args);
    }

}
