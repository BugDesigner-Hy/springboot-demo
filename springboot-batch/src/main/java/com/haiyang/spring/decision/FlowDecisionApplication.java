package com.haiyang.spring.decision;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableBatchProcessing
public class FlowDecisionApplication {

    public static void main(String[] args) {
        SpringApplication.run(FlowDecisionApplication.class, args);
    }

}
