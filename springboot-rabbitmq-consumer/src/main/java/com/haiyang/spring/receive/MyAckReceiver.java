package com.haiyang.spring.receive;/**
 * @Author: HaiYang
 * @Date: 2020/5/26 11:19
 */

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.stereotype.Component;

/**
 * @author: Administrator
 * @Date: 2020/5/26 11:19
 * @Description:
 */
@Slf4j
@Component
public class MyAckReceiver implements ChannelAwareMessageListener {

    @Override
    public void onMessage(Message message, Channel channel) throws Exception {
        long deliveryTag = message.getMessageProperties().getDeliveryTag();
        try {
            log.info("MyAckReceiver  message:{}",message);
            log.info("消费的主题消息来自:{}",message.getMessageProperties().getConsumerQueue());
//            int i  = 2/0;
            channel.basicAck(deliveryTag, true);
//			channel.basicReject(deliveryTag, true);//为true会重新放回队列
        } catch (Exception e) {
            channel.basicReject(deliveryTag, true);
            log.error(e.getMessage());
        }
    }

}
