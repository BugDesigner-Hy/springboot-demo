package com.haiyang.async.controller;/**
 * @Author: HaiYang
 * @Date: 2020/11/17 10:44
 */

import com.haiyang.async.service.AsyncService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

/**
 * @author: Administrator
 * @Date: 2020/11/17 10:44
 * @Description:
 */
@Slf4j
@RestController
public class AsyncController {

    @Autowired
    private AsyncService asyncService;

    // 存放所有的线程，用于获取结果
    List<Future<String>> lstFuture = new ArrayList<>();

    /**
     * <p>异步方法 无返回结果</p>
     * @return
     */
    @GetMapping("/async/void")
    public String asyncVoid() {
        int count = 10;
        for (int i = 0; i < count; i++) {
            asyncService.excuteVoidTask("index = " + i);
        }
        return "success";
    }

    /**
     * <p>异步 有返回值</p>
     */
    @SneakyThrows
    @GetMapping("/async/future")
    public void asyncFuture() {

        for (int i = 0; i < 10; i++) {
            CompletableFuture<String> futureValue = asyncService.excuteValueTask(" value=" + i);
            lstFuture.add(futureValue);
        }

    }

    /**
     * <p> 实时查询异步方法的返回结果
     * @return List<Future<String>> 步方法的执行结果列表
     */
    @GetMapping("/listFuture")
    public List<Future<String>> getAsyncFuture(){
        return lstFuture;
    }

}
