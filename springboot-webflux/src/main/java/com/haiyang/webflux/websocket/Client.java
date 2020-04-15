package com.haiyang.webflux.websocket;/**
 * @Author: HaiYang
 * @Date: 2020/4/14 16:17
 */

import org.springframework.web.reactive.socket.client.ReactorNettyWebSocketClient;
import org.springframework.web.reactive.socket.client.WebSocketClient;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * @author: Administrator
 * @Date: 2020/4/14 16:17
 * @Description:
 */
public class Client {

    public static void main(String[] args) throws URISyntaxException {

        WebSocketClient client = new ReactorNettyWebSocketClient();
        URI url = new URI("ws://localhost:8080/path");
        client.execute(url, session ->
                session.receive()
                        .doOnNext(System.out::println)
                        .then());
    }

}
