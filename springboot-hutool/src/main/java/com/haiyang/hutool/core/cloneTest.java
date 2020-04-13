package com.haiyang.hutool.core;/**
 * @Author: HaiYang
 * @Date: 2020/4/13 12:17
 */

import cn.hutool.core.clone.CloneRuntimeException;
import cn.hutool.core.clone.CloneSupport;
import cn.hutool.core.clone.Cloneable;
import cn.hutool.core.util.ObjectUtil;

import java.io.Serializable;

/**
 * @author: Administrator
 * @Date: 2020/4/13 12:17
 * @Description: 深拷贝 浅拷贝只针对Object类和数组对象 基本数据类型不存在此说法
 */
public class cloneTest {


    /**
     * 泛型克隆接口
     */
    private static class Cat implements Cloneable<Cat> {
        private String name = "miaomiao";
        private int age = 2;

        @Override
        public Cat clone() {
            try {
                return (Cat) super.clone();
            } catch (CloneNotSupportedException e) {
                throw new CloneRuntimeException(e);
            }
        }
    }

    /**
     * 泛型克隆类
     */
    private static class Dog extends CloneSupport<Dog> {
        private String name = "wangwang";
        private int age = 3;
    }

    private static class Apple implements Serializable {
        private String name = "apple";
    }

    public static void main(String[] args) {
        //浅拷贝
        Cat cat1 = new Cat();
        Cat cat2 = cat1.clone();
        System.out.println("cat1.equals(cat2) = " + cat1.equals(cat2));

        //浅拷贝
        Dog dog = new Dog();
        Dog dog2 = dog.clone();
        System.out.println("dog.equals(dog2) = " + dog.equals(dog2));

        //深拷贝 对象必须实现Serializable接口
        Apple apple = new Apple();
        Apple apple2 = ObjectUtil.cloneByStream(apple);
        Apple apple3 = ObjectUtil.cloneIfPossible(apple);
        Apple apple4 = ObjectUtil.clone(apple);
        System.out.println("apple.equals(apple2) = " + apple.equals(apple2));
        System.out.println("apple.equals(apple3) = " + apple.equals(apple3));
        System.out.println("apple.equals(apple4) = " + apple.equals(apple4));
    }


}
