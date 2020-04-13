package com.haiyang.hutool.other;/**
 * @Author: HaiYang
 * @Date: 2020/3/16 16:02
 */

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author: Administrator
 * @Date: 2020/3/16 16:02
 * @Description:
 */
public class ThreadPool {

    public static void main(String[] args) {
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(
            2,
            10,// cpu/ (1-x)   阻塞系数 0.8-0.9
            60,
            TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<>(),
            Executors.defaultThreadFactory(),
            new ThreadPoolExecutor.AbortPolicy()
        );
//        ExecutorService executorService = Executors.newFixedThreadPool(5);
        poolExecutor.execute(()->{
            System.out.println(Thread.currentThread().getName());
        });

        int i = Runtime.getRuntime().availableProcessors();//4
        System.out.println(i);

        List<String> list = Arrays.asList("a", "b");

    }
}
