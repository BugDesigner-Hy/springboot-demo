package com.haiyang.spring.receive;/**
 * @Author: HaiYang
 * @Date: 2020/5/21 17:28
 */

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author: Administrator
 * @Date: 2020/5/21 17:28
 * @Description:
 */
@Slf4j
@Component
@RabbitListener(queues = "topic.woman")
public class TopicWomanReceiver {

    @RabbitHandler
    public void process(String message) {
        log.info("TopicTotalReceiver receive Message:{}",message);
    }

}
