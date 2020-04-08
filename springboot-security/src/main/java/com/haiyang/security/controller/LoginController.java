package com.haiyang.security.controller;/**
 * @Author: HaiYang
 * @Date: 2020/4/8 9:44
 */

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: Administrator
 * @Date: 2020/4/8 09:44
 * @Description:
 */
@RestController
public class LoginController {

    @PostMapping("/login/success")
    public String loginSuccess(){
        return "login success";
    }

    @PostMapping("/login/fail")
    public String loginFail(){
        return "login fail";
    }

    @PostMapping("/logout-success")
    public String logoutSuccess(){
        return "logout success";
    }

    @GetMapping("/private")
    public String getPrivateResource(){
        return "Private Resource";
    }
}
