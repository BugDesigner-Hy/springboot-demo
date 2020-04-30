package com.haiyang.spring.handler;/**
 * @Author: HaiYang
 * @Date: 2020/4/27 10:33
 */

import cn.hutool.core.lang.Console;
import cn.hutool.core.util.RandomUtil;
import com.haiyang.spring.server.LikeCacheTemplate;
import com.haiyang.spring.server.LikeRedisTemplate;
import com.haiyang.spring.task.MsgAsyncTask;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.concurrent.GlobalEventExecutor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * @author: Administrator
 * @Date: 2020/4/27 10:33
 * @Description:
 */
@Slf4j
@Component
@Qualifier("customTextWebSocketFrameHandler")
@ChannelHandler.Sharable
public class TextWebSocketFrameHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

    public static ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    @Autowired
    private LikeRedisTemplate redisTemplate;
    @Autowired
    private LikeCacheTemplate cacheTemplate;
    @Autowired
    private MsgAsyncTask msgAsyncTesk;

    @Override
    protected void messageReceived(ChannelHandlerContext ctx, TextWebSocketFrame msg) {
        log.info("messageReceived");
        Channel incoming = ctx.channel();
        String userName = String.valueOf(redisTemplate.get(incoming.id()));
        channels.forEach(channel -> {
            cacheTemplate.save(userName, msg.text());
            if (channel != incoming) {
                channel.writeAndFlush(new TextWebSocketFrame("[" + userName + "]" + ":" + msg.text()));
            } else {
                channel.writeAndFlush(new TextWebSocketFrame("[you]" + ":" + msg.text()));
            }
        });
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx)  {
        log.info("handlerAdded");
        String newUserName = "User"+RandomUtil.randomNumbers(4);
        Channel incoming = ctx.channel();
        channels.add(incoming);
        channels.forEach(channel->{
            channel.writeAndFlush(new TextWebSocketFrame("[新用户] - " + newUserName + " 加入"));
        });
        redisTemplate.save(incoming.id(),newUserName);
//        channels.add(incoming);
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx)  {
        log.info("handlerRemoved");
        Channel incoming = ctx.channel();
        String removeUserName = String.valueOf(redisTemplate.get(incoming.id()));
        //用户离开
        for (Channel channel : channels) {
            channel.writeAndFlush(new TextWebSocketFrame("[用户] - " + removeUserName + " 离开"));
        }
        redisTemplate.delete(incoming.id());   //删除用户
        channels.remove(ctx.channel());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        Channel incoming = ctx.channel();
        Console.log("用户:{} 异常",redisTemplate.get(incoming.id()));
        cause.printStackTrace();
        ctx.close();
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx)  {
        Channel incoming = ctx.channel();
        Console.log("用户:{} 在线",redisTemplate.get(incoming.id()));
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        Channel incoming = ctx.channel();
        Console.log("用户:{} 掉线",redisTemplate.get(incoming.id()));
        msgAsyncTesk.saveChatMsgTask();
    }
}
