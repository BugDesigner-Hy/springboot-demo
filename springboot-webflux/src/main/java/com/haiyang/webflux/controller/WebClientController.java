package com.haiyang.webflux.controller;/**
 * @Author: HaiYang
 * @Date: 2020/4/14 17:10
 */

import com.haiyang.webflux.entity.City;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.ClientRequest;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * @author: Administrator
 * @Date: 2020/4/14 17:10
 * @Description:
 */
@Slf4j
@RestController
public class WebClientController {

    @Resource
    RestTemplate restTemplate;

    @Resource
    WebClient webClient;

    private static String baseUri = "http://localhost:8080";

    //过滤器filter
    public WebClientController() {
    }

    /**
     * 类似于restTemplate 远程调用
     *
     * @return
     */
    @GetMapping("/webclient")
    public Flux<City> findAllCity() {
        long s = System.currentTimeMillis();
        Flux<City> cityFlux = WebClient.create("localhost:8080/api/city").get().retrieve().bodyToFlux(City.class);
        long e = System.currentTimeMillis();
        log.info("webclient took time {}", e - s);//15
        return cityFlux;
    }

    /**
     * 对比restTempalte
     *
     * @return
     */
    @GetMapping("/rest")
    public List<City> getCitysByRestTemplate() {
        long s = System.currentTimeMillis();
        ResponseEntity<City[]> entity = restTemplate.getForEntity("http://localhost:8080/api/city", City[].class);
        List<City> cities = Arrays.asList(entity.getBody());
        long e = System.currentTimeMillis();
        log.info("restTemplate took time {}", e - s);//5076
        return cities;
    }

    /**
     * post传参
     *
     * @param city
     * @return
     */
    @PostMapping(value = "/post")
    public Mono<Long> post(@RequestBody Mono<City> city) {
        Mono<Long> res = WebClient.create("localhost:8080/api/save").post().body(city, City.class).retrieve().bodyToMono(Long.class);
        return res;
    }

    /**
     * URi传参 PathVariable方式传参
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/uri/{id}")
    public Mono<City> URiPathVariable(@PathVariable("id") Long id) {
        Mono<City> res = WebClient.create(baseUri).get()
                .uri(uribuilder -> uribuilder
                        .path("/api/{id}")
                        .build(id)
                )
                .retrieve()
                .bodyToMono(City.class);
        return res;
    }

    /**
     * URi传参 RequestParam方式传参 + 异常处理
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/uri/id")
    public Mono<City> URiRequestParam(@RequestParam Long id) {
        Mono<City> res = this.webClient
                .get()
                .uri(uribuilder -> uribuilder
                        .path("/api/id")
                        .queryParam("id", id)
                        .build()
                )
                .retrieve()
                .onStatus(HttpStatus::is5xxServerError, clientResponse -> {
                    return Mono.error(new Cum5xxException(clientResponse.statusCode().value() + " error code"));
                })
                .bodyToMono(City.class);
        return res;
    }

    class Cum5xxException extends Exception {
        public Cum5xxException(String message) {
            super(message);
        }
    }
}
