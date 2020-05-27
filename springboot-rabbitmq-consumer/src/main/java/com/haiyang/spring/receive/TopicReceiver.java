package com.haiyang.spring.receive;/**
 * @Author: HaiYang
 * @Date: 2020/5/21 17:09
 */

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @author: Administrator
 * @Date: 2020/5/21 17:09
 * @Description:
 */
@Slf4j
@Component
public class TopicReceiver {

    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue,
                    key={"user.#"},
                    exchange = @Exchange(type = "direct",name = "directExchange")
            )
    })
    public void process(String message){
        log.info("Topic receive Message:{}",message);
    }
}
