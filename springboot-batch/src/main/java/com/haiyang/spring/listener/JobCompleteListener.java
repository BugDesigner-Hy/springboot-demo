package com.haiyang.spring.listener;/**
 * @Author: HaiYang
 * @Date: 2020/5/6 16:46
 */

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.stereotype.Component;

/**
 * @author: Administrator
 * @Date: 2020/5/6 16:46
 * @Description:
 */
@Slf4j
@Component
public class JobCompleteListener extends JobExecutionListenerSupport {

    @Override
    public void afterJob(JobExecution jobExecution) {
        super.afterJob(jobExecution);
        log.info("after job");
    }

    @Override
    public void beforeJob(JobExecution jobExecution) {
        super.beforeJob(jobExecution);
        log.info("before job");
    }
}
