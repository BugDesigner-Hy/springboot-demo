package com.haiyang.spring.init;/**
 * @Author: HaiYang
 * @Date: 2020/4/26 15:01
 */

import com.haiyang.spring.handler.NettyServerHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.stream.ChunkedWriteHandler;
import io.netty.util.CharsetUtil;

/**
 * @author: Administrator
 * @Date: 2020/4/26 15:01
 * @Description:
 */
public class NettyClientInitializer extends ChannelInitializer<SocketChannel> {


    @Override
    protected void initChannel(SocketChannel socketChannel) {
//        socketChannel.pipeline().addLast("decoder",new StringDecoder(CharsetUtil.UTF_8));
//        socketChannel.pipeline().addLast("encoder",new StringEncoder(CharsetUtil.UTF_8));
        socketChannel.pipeline().addLast(new HttpServerCodec());
        socketChannel.pipeline().addLast(new HttpObjectAggregator(65536));
        socketChannel.pipeline().addLast(new ChunkedWriteHandler());
        socketChannel.pipeline().addLast(new WebSocketServerProtocolHandler("/ws"));
        socketChannel.pipeline().addLast(new NettyServerHandler());
    }
}
