package com.haiyang.spring.config;/**
 * @Author: HaiYang
 * @Date: 2020/4/27 10:31
 */

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author: Administrator
 * @Date: 2020/4/27 10:31
 * @Description:
 */
@Data
@Component
@ConfigurationProperties(prefix = "netty")
public class NettyBasicConfig {
    private int port;

    private int bossThread;

    private int workerThread;

    private boolean keepalive;

    private int backlog;
}
