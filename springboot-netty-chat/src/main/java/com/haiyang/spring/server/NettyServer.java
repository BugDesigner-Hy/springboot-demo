package com.haiyang.spring.server;/**
 * @Author: HaiYang
 * @Date: 2020/4/27 11:14
 */

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import java.net.InetSocketAddress;

/**
 * @author: Administrator
 * @Date: 2020/4/27 11:14
 * @Description:
 */
@Data
@Component
public class NettyServer {

    @Resource
    @Qualifier("serverBootstrap")
    private ServerBootstrap serverBootstrap;

    @Resource
    @Qualifier("tcpSocketAddress")
    private InetSocketAddress tcpPort;

    private Channel serverChannel;

    public void start() throws Exception {
        serverChannel =  serverBootstrap.bind(tcpPort).sync().channel().closeFuture().sync().channel();
    }

    @PreDestroy
    public void stop() {
        serverChannel.close();
        serverChannel.parent().close();
    }
}
