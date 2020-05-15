package com.haiyang.spring.controller;/**
 * @Author: HaiYang
 * @Date: 2020/5/15 9:35
 */

import com.haiyang.spring.service.MailService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.File;

/**
 * @author: Administrator
 * @Date: 2020/5/15 09:35
 * @Description:
 */
@RestController
public class EmailController {

    @Resource
    MailService mailService;

    /**
     * 异步发送文本邮件
     * 须在启动项添加@EnableAsync
     * @return
     */
    @GetMapping("sendText")
    public String sennTextEmail() {
        mailService.sendSimpleMail("18811435711@163.com", "EmailTest", "simpleTextEmail");
        return "send success";
    }

    /**
     * 异步发送带附件的邮件
     * 须在启动项添加@EnableAsync
     * @return
     */
    @GetMapping("sendFile")
    public String sendFile(){
        File file = new File("E:\\workspace-spring\\springboot-demo\\springboot-email\\src\\main\\resources\\test.txt");
        mailService.sendSimpleMailWithFile("18811435711@163.com", "EmailTest", "simpleTextEmail",file);
        return "send success";
    }

}
