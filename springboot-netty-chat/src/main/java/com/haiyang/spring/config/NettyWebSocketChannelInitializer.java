package com.haiyang.spring.config;/**
 * @Author: HaiYang
 * @Date: 2020/4/27 11:01
 */

import com.haiyang.spring.handler.TextWebSocketFrameHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import io.netty.handler.timeout.IdleStateHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;



import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author: Administrator
 * @Date: 2020/4/27 11:01
 * @Description:
 */
@Component
@Qualifier("nettyWebSocketChannelInitializer")
public class NettyWebSocketChannelInitializer extends ChannelInitializer<SocketChannel> {

    private static final int READ_IDLE_TIME_OUT = 60; // 读超时
    private static final int WRITE_IDLE_TIME_OUT = 0;// 写超时
    private static final int ALL_IDLE_TIME_OUT = 0; // 所有超时

    @Resource
    @Qualifier("customTextWebSocketFrameHandler")
    private TextWebSocketFrameHandler customTextWebSocketFrameHandler;

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();

        pipeline.addLast(new HttpServerCodec());
        pipeline.addLast(new HttpObjectAggregator(65536));
        pipeline.addLast(new ChunkedWriteHandler());
        // 如果被请求的端点是 "/ws",则处理该升级握手
        pipeline.addLast(new WebSocketServerProtocolHandler("/ws"));
        // //当连接在60秒内没有接收到消息时，进会触发一个 IdleStateEvent 事件，被 HeartbeatHandler 的 userEventTriggered 方法处理
        pipeline.addLast(new IdleStateHandler(READ_IDLE_TIME_OUT, WRITE_IDLE_TIME_OUT, ALL_IDLE_TIME_OUT, TimeUnit.SECONDS));
        pipeline.addLast(customTextWebSocketFrameHandler);
    }
}
