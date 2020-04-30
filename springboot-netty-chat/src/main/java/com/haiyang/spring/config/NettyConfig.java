package com.haiyang.spring.config;/**
 * @Author: HaiYang
 * @Date: 2020/4/27 11:07
 */

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author: Administrator
 * @Date: 2020/4/27 11:07
 * @Description:
 */
@Component
public class NettyConfig {

    @Resource
    private NettyBasicConfig nettyBasicConfig;

    @Bean(name = "bossGroup", destroyMethod = "shutdownGracefully")
    public NioEventLoopGroup bossGroup() {
        return new NioEventLoopGroup(nettyBasicConfig.getBossThread());
    }

    @Bean(name = "workerGroup", destroyMethod = "shutdownGracefully")
    public NioEventLoopGroup workerGroup() {
        return new NioEventLoopGroup(nettyBasicConfig.getWorkerThread());
    }

    @Bean(name = "tcpSocketAddress")
    public InetSocketAddress tcpPost() {
        return new InetSocketAddress(nettyBasicConfig.getPort());
    }

    @Bean(name = "tcpChannelOptions")
    public Map<ChannelOption<?>, Object> tcpChannelOptions() {
        Map<ChannelOption<?>, Object> options = new HashMap<ChannelOption<?>, Object>();
        options.put(ChannelOption.SO_KEEPALIVE, nettyBasicConfig.isKeepalive());
        options.put(ChannelOption.SO_BACKLOG, nettyBasicConfig.getBacklog());
        return options;
    }

    @Resource
    @Qualifier("nettyWebSocketChannelInitializer")
    private NettyWebSocketChannelInitializer nettyWebSocketChannelInitializer;

    @Resource
    NioEventLoopGroup bossGroup;

    @Resource
    NioEventLoopGroup workerGroup;

    @Resource
    Map<ChannelOption<?>, Object> tcpChannelOptions;

    @Bean(name = "serverBootstrap")
    public ServerBootstrap bootstrap() {
        ServerBootstrap b = new ServerBootstrap();
        b.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .handler(new LoggingHandler(LogLevel.DEBUG))
                .childHandler(nettyWebSocketChannelInitializer);
        Set<ChannelOption<?>> keySet = tcpChannelOptions.keySet();
        for (@SuppressWarnings("rawtypes") ChannelOption option : keySet) {
            b.option(option, tcpChannelOptions.get(option));
        }
        return b;
    }
}
