package com.haiyang.oauth.controller;/**
 * @Author: HaiYang
 * @Date: 2020/4/9 10:08
 */

import com.haiyang.oauth.config.CurrentUser;
import com.haiyang.oauth.entity.GitHubOAuth2User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

/**
 * @author: Administrator
 * @Date: 2020/4/9 10:08
 * @Description:
 */
@Slf4j
@Controller
public class OAuthLoginController {

    @ResponseBody
    @GetMapping("/private")
    public String getPrivateResource(HttpServletRequest request) {
        return "Private Resource";
    }

    /**
     * @param authorizedClient
     * @param customUser       通过直接注入的方式获取当前用户信息
     * @return
     */
    @ResponseBody
    @GetMapping("/login/success")
    public String loginSuccess(@RegisteredOAuth2AuthorizedClient("github") OAuth2AuthorizedClient authorizedClient,
                               @CurrentUser GitHubOAuth2User currentUser,//可以创建容易理解的注解来声明 或者 使用下面的方式
                               @AuthenticationPrincipal GitHubOAuth2User customUser) {
        log.info("===========SecurityContextHolder上下文=================");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        OAuth2User oAuth2User = (OAuth2User) auth.getPrincipal();
//        oAuth2User.getAttributes().forEach((k,v) -> log.info("{}:{}",k,v));
        //转化成自定义User类
        GitHubOAuth2User oAuth2User = (GitHubOAuth2User) auth.getPrincipal();
        oAuth2User.getAttributes().forEach((k, v) -> log.info("{}:{}", k, v));

        log.info("===========@AuthenticationPrincipal=================");
        customUser.getAttributes().forEach((k, v) -> log.info("{}:{}", k, v));
        log.info("============@CurrentUser===================");
        currentUser.getAttributes().forEach((k, v) -> log.info("{}:{}", k, v));
        log.info("============getAccessToken===================");
        String tokenValue = authorizedClient.getAccessToken().getTokenValue();
        log.info("tokenValue:{}", tokenValue);
        return "login success";
    }
}
