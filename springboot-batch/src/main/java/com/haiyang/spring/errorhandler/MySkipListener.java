package com.haiyang.spring.errorhandler;/**
 * @Author: HaiYang
 * @Date: 2020/5/9 9:32
 */

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.SkipListener;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @author: Administrator
 * @Date: 2020/5/9 09:32
 * @Description:
 */
@Slf4j
@Component
public class MySkipListener implements SkipListener<String,String> {

    @Override
    public void onSkipInRead(Throwable throwable) {

    }

    @Override
    public void onSkipInWrite(String s, Throwable throwable) {

    }

    @Override
    public void onSkipInProcess(String s, Throwable throwable) {

        log.info("{} got exception {}",s,throwable.getMessage());

    }
}
