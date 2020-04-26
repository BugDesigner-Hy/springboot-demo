package com.haiyang.spring;

import com.haiyang.spring.server.NettyClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringbootNettyClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootNettyClientApplication.class, args);
        //启动netty客户端
        NettyClient nettyClient = new NettyClient();
        nettyClient.start();
    }

}
