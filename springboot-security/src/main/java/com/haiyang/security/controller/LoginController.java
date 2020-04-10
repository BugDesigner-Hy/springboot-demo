package com.haiyang.security.controller;/**
 * @Author: HaiYang
 * @Date: 2020/4/8 9:44
 */

import com.wf.captcha.*;
import com.wf.captcha.base.Captcha;
import com.wf.captcha.utils.CaptchaUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author: Administrator
 * @Date: 2020/4/8 09:44
 * @Description:
 */
@RestController
public class LoginController {

    @PostMapping("/login/success")
    public String loginSuccess() {
        return "login success";
    }

    @PostMapping("/login/fail")
    public String loginFail() {
        return "login fail";
    }

    @PostMapping("/logout-success")
    public String logoutSuccess() {
        return "logout success";
    }

    @GetMapping("/private")
    public String getPrivateResource() {
        return "Private Resource";
    }

    @GetMapping("/captcha")
    public void captcha(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException {
//        前后端分离项目建议不要存储在session中，存储在redis中，redis存储需要一个key，key一同返回给前端用于验证输入
        SpecCaptcha specCaptcha = new SpecCaptcha(130, 48, 5);
        specCaptcha.setCharType(Captcha.TYPE_ONLY_NUMBER);//纯数字

        String verCode = specCaptcha.text().toLowerCase();
        System.out.println("verCode = " + verCode);
        // 存入redis并设置过期时间为30分钟
//        String key = UUID.randomUUID().toString();
//        redisUtil.setEx(key, verCode, 30, TimeUnit.MINUTES);
        CaptchaUtil.out(specCaptcha, request, response);
    }

    @GetMapping("/captcha/more")
    public void gifCaptcha(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException {

        // 使用gif验证码
        GifCaptcha gifCaptcha = new GifCaptcha(130, 48, 4);
        // 中文类型
        ChineseCaptcha chineseCaptcha = new ChineseCaptcha(130, 48);

        // 中文gif类型
        ChineseGifCaptcha chineseGifCaptcha = new ChineseGifCaptcha(130, 48);

        // 算术类型
        ArithmeticCaptcha arithmeticCaptcha = new ArithmeticCaptcha(130, 48);
        arithmeticCaptcha.setLen(2);  // 几位数运算，默认是两位
        arithmeticCaptcha.getArithmeticString();  // 获取运算的公式：3+2=?
        arithmeticCaptcha.text();  // 获取运算的结果：5
        CaptchaUtil.out(arithmeticCaptcha, request, response);
    }

    @PostMapping("/code-check")
    public String login(String username, String password, String verCode, HttpServletRequest request) {
        //1.使用redis来获取code

//        String redisCode = redisUtil.get(verKey);
        // 判断验证码
//        if (verCode==null || !redisCode.equals(verCode.trim().toLowerCase())) {
//            return "验证码不正确";
//        }


        //2.session方式获取code

        if (!CaptchaUtil.ver(verCode, request)) {
            CaptchaUtil.clear(request);  // 清除session中的验证码
            return "验证码不正确";
        }
        return "success";
    }

}
