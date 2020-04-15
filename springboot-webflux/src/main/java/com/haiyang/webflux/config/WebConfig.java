package com.haiyang.webflux.config;/**
 * @Author: HaiYang
 * @Date: 2020/4/14 15:59
 */

import com.haiyang.webflux.controller.DataController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.CacheControl;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.validation.MessageCodesResolver;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.method.HandlerTypePredicate;
import org.springframework.web.reactive.config.*;
import org.springframework.web.reactive.function.client.ClientRequest;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.result.method.annotation.ArgumentResolverConfigurer;
import reactor.core.publisher.Mono;

import java.util.concurrent.TimeUnit;

/**
 * @author: Administrator
 * @Date: 2020/4/14 15:59
 * @Description:
 */
@Slf4j
@Configuration
@EnableWebFlux
public class WebConfig implements WebFluxConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")
                .allowedOrigins("*")
                .allowedMethods("GET", "PUT", "POST")
                .allowCredentials(true).maxAge(3600);
    }

    @Override
    public void configurePathMatching(PathMatchConfigurer configurer) {
        configurer
                .setUseCaseSensitiveMatch(true)
                .setUseTrailingSlashMatch(false)
                .addPathPrefix("/api",
                        HandlerTypePredicate.forAnnotation(RestController.class));
    }


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        registry.addResourceHandler("/resources/**")
                .addResourceLocations("/public", "classpath:/static/")
                .setCacheControl(CacheControl.maxAge(365, TimeUnit.DAYS));

    }

    @Override
    public void configureArgumentResolvers(ArgumentResolverConfigurer configurer) {

    }

    @Override
    public void configureHttpMessageCodecs(ServerCodecConfigurer configurer) {

    }

    @Override
    public void addFormatters(FormatterRegistry registry) {

    }

    @Override
    public Validator getValidator() {
        return null;
    }

    @Override
    public MessageCodesResolver getMessageCodesResolver() {
        return null;
    }

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {

    }

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    @Value("${webclient.baseUri}")
    private String baseUri;

    @Bean
    public WebClient webClient(){
        return WebClient.builder()
                .baseUrl(baseUri)
                .filter(logRequest())
                .filter(logResponse())
                .filter((request,next)->{
                    ClientRequest filtered = ClientRequest.from(request)
                            .header("foo", "bar")
                            .build();
                    return next.exchange(filtered);
                })
                .build();
    }

    private ExchangeFilterFunction logRequest() {
        return ExchangeFilterFunction.ofRequestProcessor(clientRequest -> {
            log.info("Request: {}:{}", clientRequest.method(), clientRequest.url());
            clientRequest.headers()
                    .forEach((name, values) -> values.forEach(value -> log.info("Request:{}:{}", name, value)));
            return Mono.just(clientRequest);
        });
    }

    private ExchangeFilterFunction logResponse() {
        return ExchangeFilterFunction.ofResponseProcessor(clientResponse -> {
            clientResponse.headers().asHttpHeaders()
                    .forEach((name, values) -> values.forEach(value -> log.info("Response:{}={}", name, value)));
            return Mono.just(clientResponse);
        });
    }
}
