package com.haiyang.spring.listener;/**
 * @Author: HaiYang
 * @Date: 2020/5/8 11:29
 */

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.annotation.AfterChunk;
import org.springframework.batch.core.annotation.BeforeChunk;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.stereotype.Component;

/**
 * @author: Administrator
 * @Date: 2020/5/8 11:29
 * @Description:
 */
@Slf4j
@Component
public class MyChunkListener {

    @BeforeChunk
    public void beforeChunk(ChunkContext chunkContext) {
        log.info("{} before chunk running....", chunkContext.getStepContext().getJobName());
    }

    @AfterChunk
    public void afterChunk(ChunkContext chunkContext) {
        log.info("{} after chunk running....", chunkContext.getStepContext().getJobName());
    }
}
