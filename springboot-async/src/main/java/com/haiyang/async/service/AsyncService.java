package com.haiyang.async.service;/**
 * @Author: HaiYang
 * @Date: 2020/11/17 10:45
 */

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

/**
 * @author: Administrator
 * @Date: 2020/11/17 10:45
 * @Description:
 */
@Slf4j
@Service
public class AsyncService {

    // 指定使用beanname为customExecutor的线程池
    @Async("customExecutor")
    public void excuteVoidTask(String message) {
        log.info("excuteVoidTask, {}", message);
        try {
            Thread.sleep(RandomUtil.randomInt(1000,5000));
        } catch (InterruptedException e) {
            log.error("excuteVoidTask error: ", e);
        }

    }

    @Async("customExecutor")
    public CompletableFuture<String> excuteValueTask(String message) throws InterruptedException {
        log.info("excuteValueTask: {}", message);
        Thread.sleep(RandomUtil.randomInt(10*1000,20*1000));
        return CompletableFuture.completedFuture("excuteValueTask: " + message);
    }

}
