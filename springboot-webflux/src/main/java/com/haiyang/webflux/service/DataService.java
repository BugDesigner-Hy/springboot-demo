package com.haiyang.webflux.service;/**
 * @Author: HaiYang
 * @Date: 2020/4/14 11:22
 */

import com.haiyang.webflux.entity.City;
import com.haiyang.webflux.repository.DataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author: Administrator
 * @Date: 2020/4/14 11:22
 * @Description:
 */
@Service
public class DataService {

    @Autowired
    DataRepository dataRepository;

    public Mono<String> helloWebFlux() {
        return Mono.justOrEmpty("hello webFlux");
    }

    public Mono<ServerResponse> testWebFlux(ServerRequest request) {
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .bodyValue("test success");
    }

    public Mono<Long> save(City city) {
        return Mono.create(cityMonoSink ->
                cityMonoSink.success(dataRepository.save(city))
        );
    }

    public Mono<City> findCityById(Long id) {
        return Mono.justOrEmpty(dataRepository.findCityById(id));
    }

    public Flux<City> findAllCity() {
        return Flux.fromIterable(dataRepository.findAll());
    }

    public Mono<Long> modifyCity(City city) {
        return Mono.create(cityMonosink ->
                cityMonosink.success(dataRepository.updateCity(city))
        );
    }

    public Mono<Long> deleteCity(Long id) {
        return Mono.create(cityMonoSink -> cityMonoSink.success(dataRepository.deleteCity(id)));
    }
}
