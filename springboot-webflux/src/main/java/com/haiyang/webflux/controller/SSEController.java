package com.haiyang.webflux.controller;/**
 * @Author: HaiYang
 * @Date: 2020/4/14 16:43
 */

import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.UUID;

/**
 * @author: Administrator
 * @Date: 2020/4/14 16:43
 * @Description:
 */
@RestController
public class SSEController {

    /**
     * 单向通信 只从从服务器到客户端 直到一方断开连接
     * 加上媒体类型 呈现方式发生变化
     * @return
     */
    @GetMapping(value = "sse",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> serviceSentEvents(){
        return Flux.interval(Duration.ofMillis(1000)).map(index -> "->"+index);
    }

    @GetMapping("/sse1")
    public Flux<ServerSentEvent<String>> sse(){
        Flux<Long> interval = Flux.interval(Duration.ofMillis(1000));
        Flux<ServerSentEvent<String>> serverSentEventFlux = interval.map(val -> {
            return ServerSentEvent.<String>builder()
                    .id(UUID.randomUUID().toString())
                    .event("sse-evevt")
                    .data(val.toString())
                    .build();
        });
        return serverSentEventFlux;
    }




}
