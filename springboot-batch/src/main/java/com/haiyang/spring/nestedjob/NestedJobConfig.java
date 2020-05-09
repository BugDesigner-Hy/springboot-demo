package com.haiyang.spring.nestedjob;/**
 * @Author: HaiYang
 * @Date: 2020/5/8 10:10
 */

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.JobStepBuilder;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import javax.annotation.Resource;

/**
 * @author: Administrator
 * @Date: 2020/5/8 10:10
 * @Description:
 */
@Slf4j
@Configuration
public class NestedJobConfig {

    @Resource
    private JobBuilderFactory jobBuilderFactory;

    @Resource
    private StepBuilderFactory stepBuilderFactory;

    @Resource
    private JobLauncher jobLauncher;

    @Resource
    private JobRepository jobRepository;

    @Resource
    private PlatformTransactionManager platformTransactionManager;

    /**
     * 在application.yml中配置
     * batch:
     * job:
     * names: parentJob
     * 这样避免多个job同时启动 只在父job启动
     *
     * @return
     */
    @Bean
    public Job parentJob() {
        return jobBuilderFactory.get("parentJob")
                .start(childJobStep1())
                .next(childJobStep2())
                .build();
    }

    @Bean
    public Step childJobStep1() {
        return new JobStepBuilder(new StepBuilder("childJobStep1"))
                .job(childJob1())
                .launcher(jobLauncher)
                .repository(jobRepository)
                .transactionManager(platformTransactionManager)
                .build();
    }

    @Bean
    public Step childJobStep2() {
        return new JobStepBuilder(new StepBuilder("childJobStep2"))
                .job(childJob2())
                .launcher(jobLauncher)
                .repository(jobRepository)
                .transactionManager(platformTransactionManager)
                .build();
    }

    @Bean
    public Job childJob1() {
        return jobBuilderFactory.get("childJob1")
                .start(step1OnChildJob1())
                .build();
    }

    @Bean
    public Job childJob2() {
        return jobBuilderFactory.get("childJob2")
                .start(step1OnChildJob2())
                .next(step2OnChildJob2())
                .build();
    }


    @Bean
    public Step step1OnChildJob1() {
        return stepBuilderFactory.get("step1OnChildJob1")
                .tasklet((stepContribution, chunkContext) -> {
                    log.info("{} has been executed on thread {}", chunkContext.getStepContext().getStepName(), Thread.currentThread().getName());
                    return RepeatStatus.FINISHED;
                }).build();
    }

    @Bean
    public Step step1OnChildJob2() {
        return stepBuilderFactory.get("step1OnChildJob2")
                .tasklet((stepContribution, chunkContext) -> {
                    log.info("{} has been executed on thread {}", chunkContext.getStepContext().getStepName(), Thread.currentThread().getName());
                    return RepeatStatus.FINISHED;
                }).build();
    }

    @Bean
    public Step step2OnChildJob2() {
        return stepBuilderFactory.get("step2OnChildJob2")
                .tasklet((stepContribution, chunkContext) -> {
                    log.info("{} has been executed on thread {}", chunkContext.getStepContext().getStepName(), Thread.currentThread().getName());
                    return RepeatStatus.FINISHED;
                }).build();
    }
}
