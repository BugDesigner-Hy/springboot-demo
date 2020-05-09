package com.haiyang.spring.output.tofile;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableBatchProcessing
public class ToFileApplication {

    public static void main(String[] args) {
        SpringApplication.run(ToFileApplication.class, args);
    }

}
