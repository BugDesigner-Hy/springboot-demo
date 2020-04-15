package com.haiyang.webflux.controller;/**
 * @Author: HaiYang
 * @Date: 2020/4/14 12:39
 */

import com.haiyang.webflux.entity.City;
import com.haiyang.webflux.service.DataService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;

/**
 * @author: Administrator
 * @Date: 2020/4/14 12:39
 * @Description:
 */
@RestController
public class DataController {

    @Resource
    DataService dataService;

    @GetMapping("/hello")
    public Mono<String> helloWebFlux(){
        return dataService.helloWebFlux();
    }

    @GetMapping(value = "/{id}")
    public Mono<City> PathVariable(@PathVariable("id") Long id) {
        return dataService.findCityById(id);
    }

    @GetMapping(value = "/id")
    public Mono<City> findCityById(@RequestParam Long id) {
        return dataService.findCityById(id);
    }

    @GetMapping("/city")
    public Flux<City> findAllCity() throws InterruptedException {
        Thread.sleep(5000);
        return dataService.findAllCity();
    }

    @PostMapping(value = "/save")
    public Mono<Long> saveCity(@RequestBody City city) {
        return dataService.save(city);
    }

    @PutMapping()
    public Mono<Long> modifyCity(@RequestBody City city) {
        return dataService.modifyCity(city);
    }

    @DeleteMapping(value = "/{id}")
    public Mono<Long> deleteCity(@PathVariable("id") Long id) {
        return dataService.deleteCity(id);
    }

}
