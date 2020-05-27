package com.haiyang.spring.receive;/**
 * @Author: HaiYang
 * @Date: 2020/5/26 10:31
 */

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author: Administrator
 * @Date: 2020/5/26 10:31
 * @Description:
 */
@Slf4j
@Component
@RabbitListener(queues = "fanout.B")
public class FanoutReceiverB {

    @RabbitHandler
    public void process(String message) {
        log.info("FanoutReceiverB消费者收到消息:{} ",message);
    }
}
