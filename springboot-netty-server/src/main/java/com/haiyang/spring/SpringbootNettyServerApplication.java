package com.haiyang.spring;

import com.haiyang.spring.server.NettyServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.net.InetSocketAddress;

@SpringBootApplication
public class SpringbootNettyServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootNettyServerApplication.class, args);
        NettyServer nettyServer = new NettyServer();
        nettyServer.start(new InetSocketAddress("127.0.0.1",8083));
    }

}
