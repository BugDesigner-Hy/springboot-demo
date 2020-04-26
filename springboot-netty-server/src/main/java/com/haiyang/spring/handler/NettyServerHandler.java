package com.haiyang.spring.handler;/**
 * @Author: HaiYang
 * @Date: 2020/4/26 14:53
 */

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * @author: Administrator
 * @Date: 2020/4/26 14:53
 * @Description:
 */
@Slf4j
public class NettyServerHandler extends SimpleChannelInboundHandler {
    @Override
    protected void messageReceived(ChannelHandlerContext ctx, Object msg) throws Exception {
        log.info("收到客户端的信息:{}",msg);
        log.info("服务端发送信息:我是服务端 我收到了你的信息");
        ctx.writeAndFlush("我是服务端 我收到了你的信息");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
        log.error("{} exceptionCaught:{}",ctx.name(),cause.getMessage());
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        log.info("{} channelActive...",ctx.name());
    }
}
