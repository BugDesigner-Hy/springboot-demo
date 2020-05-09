package com.haiyang.spring.schedule;/**
 * @Author: HaiYang
 * @Date: 2020/5/9 11:13
 */

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author: Administrator
 * @Date: 2020/5/9 11:13
 * @Description:
 */
@Slf4j
@Component
public class MyStepLinstener implements StepExecutionListener {

    private Map<String, JobParameter> parameters;

    @Override
    public void beforeStep(StepExecution stepExecution) {
        parameters = stepExecution.getJobParameters().getParameters();
        log.info("{} get parameters:{} beforeStep", stepExecution.getJobExecution().getJobInstance().getJobName(), parameters);

    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        log.info("{} get parameters:{} afterStep", stepExecution.getJobExecution().getJobInstance().getJobName(), parameters);
        return null;
    }
}
