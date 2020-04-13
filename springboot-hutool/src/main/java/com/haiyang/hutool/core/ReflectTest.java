package com.haiyang.hutool.core;/**
 * @Author: HaiYang
 * @Date: 2020/4/13 15:22
 */

import cn.hutool.core.util.ReflectUtil;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author: Administrator
 * @Date: 2020/4/13 15:22
 * @Description:
 */
public class ReflectTest {

    public void sayHello(String who){
        System.out.println("hello " + who);
    }

    public static void main(String[] args) {


        Method[] methods = ReflectUtil.getMethods(ReflectTest.class);
        Arrays.stream(methods).forEach(method -> {
            System.out.println("method.getName() = " + method.getName());
        });

        ReflectTest reflectTest = ReflectUtil.newInstance(ReflectTest.class);

        ReflectUtil.invoke(reflectTest,"sayHello","hy");

    }

}
