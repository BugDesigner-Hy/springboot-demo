package com.haiyang.spring;

import com.haiyang.spring.server.NettyServer;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
@MapperScan("com.haiyang.spring.mapper")
public class SpringbootNettyChatApplication {

    public static void main(String[] args) throws Exception {
        ConfigurableApplicationContext ctx = SpringApplication.run(SpringbootNettyChatApplication.class, args);
        NettyServer nettyServer = ctx.getBean(NettyServer.class);
        nettyServer.start();
    }

}
