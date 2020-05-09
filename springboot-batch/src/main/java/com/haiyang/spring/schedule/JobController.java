package com.haiyang.spring.schedule;/**
 * @Author: HaiYang
 * @Date: 2020/5/9 10:51
 */

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.*;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author: Administrator
 * @Date: 2020/5/9 10:51
 * @Description:
 */
@RestController
public class JobController {

    @Resource
    private JobLauncher jobLauncher;

    @Resource
    private JobOperator jobOperator;

    @Resource
    private Job jobScheduleJob;

    @GetMapping("/jobLauncher")
    public String startJob() throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {
        JobParameters jobParameters = new JobParametersBuilder()
                .addString("name", "haiyang11").toJobParameters();
        jobLauncher.run(jobScheduleJob, jobParameters);
        return "success";
    }

    @GetMapping("/jobOperator")
    public void startJob2() throws JobParametersInvalidException, JobInstanceAlreadyExistsException, NoSuchJobException {
        jobOperator.start("jobScheduleJob", "name=bsj");
    }

    @Scheduled(fixedRate = 5000)
    @GetMapping("/scheduleJob")
    public void scheduleJob() throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException, NoSuchJobException, JobParametersNotFoundException {
        jobOperator.startNextInstance("jobScheduleJob");
    }

}
