package com.haiyang.spring.receive;/**
 * @Author: HaiYang
 * @Date: 2020/5/21 17:21
 */

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author: Administrator
 * @Date: 2020/5/21 17:21
 * @Description:
 */
@Slf4j
@Component
@RabbitListener(queues = "topic.man")
public class TopicManReceiver {

    @RabbitHandler
    public void process(String message) {
        log.info("TopicManReceiver receive Message:{}",message);
    }

}
