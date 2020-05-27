package com.haiyang.spring.controller;/**
 * @Author: HaiYang
 * @Date: 2020/5/21 16:50
 */

import com.haiyang.spring.pojo.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author: Administrator
 * @Date: 2020/5/21 16:50
 * @Description:
 */
@RestController
public class SendMessageController {

    @Autowired
    RabbitTemplate rabbitTemplate;

    /**
     * 消息推送成功
     * @return
     */
    @GetMapping("sendMessage")
    public String sendDirectMessage(){
        Message message = new Message(1, "test message",new Date());
        rabbitTemplate.convertAndSend("TestDirectExchange","TestDirectRouting",message.toString());
        return "send success";
    }

    /**
     * 发送Topic类信息
     * @return
     */
    @GetMapping("/sendTopicMessage1")
    public String sendTopicMessage1() {
        Message message = new Message(2, "man",new Date());
        rabbitTemplate.convertAndSend("topicExchange", "topic.man", message.toString());
        return "ok";
    }

    @GetMapping("/sendTopicMessage2")
    public String sendTopicMessage2() {
        Message message = new Message(3, "woman",new Date());
        rabbitTemplate.convertAndSend("topicExchange", "topic.woman", message.toString());
        return "ok";
    }

    /**
     * 发送Fanout扇形类信息
     * @return
     */
    @GetMapping("/sendFanoutMessage")
    public String sendFanoutMessage() {
        Message message = new Message(4, "FanoutMessage",new Date());
        rabbitTemplate.convertAndSend("fanoutExchange", null, message.toString());
        return "ok";
    }

    /**
     * 消息确认机制
     */
    /**
     * 消息推送到server，但是在server里找不到交换机
     * 消息推送到sever，交换机和队列啥都没找到
     * 两种情况类似
     * 这种情况触发的是 ConfirmCallback 回调函数
     * @return
     */
    @GetMapping("/TestMessageAck")
    public String TestMessageAck() {
        Message message = new Message(5, "message: non-existent-exchange test message",new Date());
        rabbitTemplate.convertAndSend("non-existent-exchange", "TestDirectRouting", message.toString());
        return "ok";
    }

    /**
     * 消息推送到server，找到交换机了，但是没找到队列
     * 这种情况触发的是 ConfirmCallback和RetrunCallback两个回调函数
     * @return
     */
    @GetMapping("/TestMessageAck2")
    public String TestMessageAck2() {
        Message message = new Message(6, "message: lonelyDirectExchange test message",new Date());
        rabbitTemplate.convertAndSend("lonelyDirectExchange", "TestDirectRouting", message.toString());
        return "ok";
    }



}
