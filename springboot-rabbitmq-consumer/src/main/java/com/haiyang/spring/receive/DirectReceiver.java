package com.haiyang.spring.receive;/**
 * @Author: HaiYang
 * @Date: 2020/5/21 17:09
 */

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.stereotype.Component;

/**
 * @author: Administrator
 * @Date: 2020/5/21 17:09
 * @Description:
 */
@Slf4j
@Component
//@RabbitListener(queues = "TestDirectQueue")
@RabbitListener(queuesToDeclare = @Queue(value = "TestDirectQueue",durable = "true"))
public class DirectReceiver {

    @RabbitHandler
    public void process(String message){
        log.info("receive Message:{}",message);
    }
}
