package com.haiyang.spring.config;/**
 * @Author: HaiYang
 * @Date: 2020/5/19 16:05
 */

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.session.events.SessionCreatedEvent;
import org.springframework.session.events.SessionDeletedEvent;
import org.springframework.session.events.SessionExpiredEvent;

import javax.servlet.http.HttpSession;

/**
 * @author: Administrator
 * @Date: 2020/5/19 16:05
 * @Description:
 */
@Slf4j
@Configuration
//@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 30)
public class SessionConfiguration {

    @EventListener
    public void onSessionExpired(SessionExpiredEvent expiredEvent) {
        String sessionId = expiredEvent.getSessionId();
        log.info("session失效:{}", sessionId);
    }

    @EventListener
    public void onSessionCreated(SessionCreatedEvent createdEvent) {
        String sessionId = createdEvent.getSessionId();
        log.info("session创建:{}", sessionId);
    }

    @EventListener
    public void onSessionDeleted(SessionDeletedEvent deletedEvent) {
        String sessionId = deletedEvent.getSessionId();
        log.info("session删除:{}", sessionId);
    }

}
