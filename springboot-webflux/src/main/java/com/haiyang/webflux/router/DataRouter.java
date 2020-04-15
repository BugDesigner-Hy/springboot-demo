package com.haiyang.webflux.router;/**
 * @Author: HaiYang
 * @Date: 2020/4/14 11:25
 */

import com.haiyang.webflux.service.DataService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

/**
 * @author: Administrator
 * @Date: 2020/4/14 11:25
 * @Description:
 */
@Configuration
public class DataRouter {

    @Bean
    public RouterFunction<ServerResponse> routerData(DataService dataService) {
        return RouterFunctions
                .route(
                        RequestPredicates
                                .GET("/test")
                                .and(RequestPredicates.accept(MediaType.APPLICATION_JSON)),
                        dataService::testWebFlux
                );
    }
}
