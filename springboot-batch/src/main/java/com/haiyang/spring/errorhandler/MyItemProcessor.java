package com.haiyang.spring.errorhandler;/**
 * @Author: HaiYang
 * @Date: 2020/5/8 17:39
 */

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

/**
 * @author: Administrator
 * @Date: 2020/5/8 17:39
 * @Description:
 */
@Component
@Slf4j
public class MyItemProcessor implements ItemProcessor<String, String> {

    private int count = 0;

    private int retry = 0;

    @Override
    public String process(String str) throws Exception {

        count++;
        if (count % 2 == 0) {
//            log.info("str:{} retry:{}", str, retry);
            return str;
        } else {
//            retry++;
            throw new Exception("process str :" + str + " failed :" + count);
        }
    }
}
