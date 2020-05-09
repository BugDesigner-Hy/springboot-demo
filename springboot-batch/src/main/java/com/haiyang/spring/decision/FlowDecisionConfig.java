package com.haiyang.spring.decision;/**
 * @Author: HaiYang
 * @Date: 2020/5/8 10:10
 */

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.core.job.flow.FlowExecutionStatus;
import org.springframework.batch.core.job.flow.JobExecutionDecider;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;

import javax.annotation.Resource;

/**
 * @author: Administrator
 * @Date: 2020/5/8 10:10
 * @Description:
 */
@Slf4j
@Configuration
public class FlowDecisionConfig {

    @Resource
    private JobBuilderFactory jobBuilderFactory;

    @Resource
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job flowDecisionJob() {
        return jobBuilderFactory.get("flowDecisionJob")
                .start(step1())
                .next(myDecider())
                .from(myDecider()).on("EVEN").to(step2())
                .from(myDecider()).on("ODD").to(step3())
                .from(step3()).on("*").to(myDecider())
                .end()
                .build();
    }

    @Bean
    public JobExecutionDecider myDecider() {
        return new JobExecutionDecider() {
            int count = 0;
            @Override
            public FlowExecutionStatus decide(JobExecution jobExecution, StepExecution stepExecution) {
                count++;
                if (count % 2 == 0) {
                    return new FlowExecutionStatus("EVEN");
                } else {
                    return new FlowExecutionStatus("ODD");
                }
            }
        };
    }

    @Bean
    public Step step1() {
        return stepBuilderFactory.get("step1")
                .tasklet((stepContribution, chunkContext) -> {
                    log.info("{} has been executed ", chunkContext.getStepContext().getStepName());
                    return RepeatStatus.FINISHED;
                }).build();
    }

    @Bean
    public Step step2() {
        return stepBuilderFactory.get("step2")
                .tasklet((stepContribution, chunkContext) -> {
                    log.info("{} has been executed ", chunkContext.getStepContext().getStepName());
                    return RepeatStatus.FINISHED;
                }).build();
    }

    @Bean
    public Step step3() {
        return stepBuilderFactory.get("step3")
                .tasklet((stepContribution, chunkContext) -> {
                    log.info("{} has been executed ", chunkContext.getStepContext().getStepName());
                    return RepeatStatus.FINISHED;
                }).build();
    }
}
