package com.haiyang.security.config;/**
 * @Author: HaiYang
 * @Date: 2020/4/9 15:10
 */

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.messaging.MessageSecurityMetadataSourceRegistry;
import org.springframework.security.config.annotation.web.socket.AbstractSecurityWebSocketMessageBrokerConfigurer;

/**
 * @author: Administrator
 * @Date: 2020/4/9 15:10
 * @Description: 对WebSocket的授权支持
 */
//@Configuration
//public class WebSocketSecurityConfig extends AbstractSecurityWebSocketMessageBrokerConfigurer {
//
//    @Override
//    protected void configureInbound(MessageSecurityMetadataSourceRegistry messages) {
//        messages
//                //任何没有目的地的消息（即消息类型为MESSAGE或SUBSCRIBE以外的任何消息）都将要求用户进行身份验证
//                .nullDestMatcher().authenticated()
//                .simpSubscribeDestMatchers("/user/queue/errors").permitAll()
//                //任何以SUBSCRIBE类型开头的以“ / user /”或“ / topic / friends /”开头的消息都需要ROLE_USER
//                .simpDestMatchers("/app/**").hasRole("USER")
//                .simpSubscribeDestMatchers("/user/**", "/topic/friends/*").hasRole("USER")
//                .anyMessage().denyAll();
//
//    }
//}
