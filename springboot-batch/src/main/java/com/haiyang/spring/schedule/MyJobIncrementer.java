package com.haiyang.spring.schedule;/**
 * @Author: HaiYang
 * @Date: 2020/5/9 11:49
 */

import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersIncrementer;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author: Administrator
 * @Date: 2020/5/9 11:49
 * @Description:
 */
@Component
public class MyJobIncrementer implements JobParametersIncrementer {

    @Override
    public JobParameters getNext(JobParameters jobParameters) {
        JobParameters parameters = (jobParameters == null) ? new JobParameters() : jobParameters;
        return new JobParametersBuilder(parameters).addDate("curDate", new Date()).toJobParameters();
    }

}
