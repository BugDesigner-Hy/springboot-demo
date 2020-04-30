package com.haiyang.spring.controller;


import com.baomidou.mybatisplus.extension.api.R;
import com.haiyang.spring.entity.UserMsg;
import com.haiyang.spring.server.UserMsgServer;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author haiyang
 * @since 2020-04-27
 */
@RestController
@RequestMapping("/test/userMsg")
public class UserMsgController {

    @Resource
    UserMsgServer userMsgServer;

    @PostMapping("/list")
    public R getUserMsg(){
        List<UserMsg> list = userMsgServer.list();
        return R.ok(list).setCode(200);
    }
}

