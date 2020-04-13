package com.haiyang.hutool.other;

import cn.hutool.core.lang.Console;

/**
 * 饿汉式 静态常量 线程安全
 * 优点：在类装载的时候就完成实例化 避免线程同步问题
 * 缺点：容易造成内存浪费 没有达到懒加载
 */
class Singleton {

    private final static Singleton singleton = new Singleton();

    private Singleton() {
        System.out.println("init singleton");
    }

    public static Singleton getSingleton() {
        return singleton;
    }
}

/**
 * 饿汉式
 * 静态代码块实现初始化实例
 * 容易造成内存浪费
 */
class SingletonLazyLoad {

    private static SingletonLazyLoad singleton;

    static {
        singleton = new SingletonLazyLoad();
    }

    private SingletonLazyLoad() {
        System.out.println("init singleton2");
    }

    public static SingletonLazyLoad getSingleton() {
        return singleton;
    }
}

/**
 * 懒汉式 线程不安全型写法
 * 线程不安全 也就意味着他某种程度不算是单例模式
 * 实际开发不要使用
 */
class Singleton3 {

    private static Singleton3 singleton;

    private Singleton3() {
        System.out.println("init singleton3");
    }

    public static Singleton3 getSingleton() {
        if (singleton == null) {
            singleton = new Singleton3();
        }
        return singleton;
    }
}

/**
 * 懒汉式 线程安全型
 * 同步静态方法
 * 每次都要加锁 效率太低
 */
class Singleton4 {

    private static Singleton4 singleton;

    private Singleton4() {
        System.out.println("init singleton4");
    }

    public static synchronized Singleton4 getSingleton() {
        if (singleton == null) {
            singleton = new Singleton4();
        }
        return singleton;
    }
}

/**
 * 懒汉式 线程安全型
 * 双重检查
 * 提高效率
 */
class Singleton5 {

    private static volatile Singleton5 singleton;

    private Singleton5() {
        System.out.println("init singleton5");
    }

    public static Singleton5 getSingleton() {
        if (singleton == null) {
            synchronized (Singleton5.class) {
                if (singleton == null) {
                    singleton = new Singleton5();
                }

            }
        }
        return singleton;
    }
}

/**
 * 静态内部类 懒汉模式
 * 同一个类加载器类加载的时候只会有一个线程去执行<init>方法
 * 所以可以确保线程安全
 * 缺点：但是他没法传递参数
 */
class Singleton6 {

    private Singleton6() {
        System.out.println("init singleton6");
    }

    public static Singleton6 getInstance() {
        return inner.instance;
    }

    private static class inner {
        static {
            System.out.println("inner");
        }

        private final static Singleton6 instance = new Singleton6();
    }
}

/**
 * 枚举类单例模式
 * 枚举类隐藏了私有的构造器
 * 枚举类的域 是相应类型的一个实例对象
 */
enum Singleton7 {
    INSTANCE;
}

class Dog{

    public void say() {
        System.out.println("汪汪");
    }
}

/**
 * 单例模式保证系统内存里该类只有一个对象实例
 * 节省系统资源 对于一些需要频繁创建和销毁的对象
 * 比如 工具类对象 连接数据源对象等 可以使用单例模式提高系统性能
 */
public class SingletonTest {

    public static void main(String[] args) {

        int a = 655;
        int b = 234;
        int c = a/b;
        System.out.println("c = " + c);

        Dog dog = cn.hutool.core.lang.Singleton.get(Dog.class);
        Console.log(dog == cn.hutool.core.lang.Singleton.get(Dog.class));
        Singleton s1 = Singleton.getSingleton();
//        Singleton s2 = Singleton.getSingleton();
//        System.out.println(s1 == s2);//true
//
//        SingletonLazyLoad sl_a = SingletonLazyLoad.getSingleton();
//        SingletonLazyLoad sl_b = SingletonLazyLoad.getSingleton();
//        System.out.println(sl_a == sl_b);//true
//
//
//        Singleton3 s3_a = Singleton3.getSingleton();
//        Singleton3 s3_b = Singleton3.getSingleton();
//        System.out.println(s3_a == s3_b);//true
//
//        Singleton4 s4_a = Singleton4.getSingleton();
//        Singleton4 s4_b = Singleton4.getSingleton();
//        System.out.println(s4_a == s4_b);//true
//
//        Singleton5 s5_a = Singleton5.getSingleton();
//        Singleton5 s5_b = Singleton5.getSingleton();
//        System.out.println(s5_a == s5_b);//true
//
//        Singleton6 s6_a = Singleton6.getInstance();
//        Singleton6 s6_b = Singleton6.getInstance();
//        System.out.println(s6_a == s6_b);//true
//
//        //jdk中Runtime类就是单例模式-饿汉式
//        System.out.println(Runtime.getRuntime().availableProcessors());//cpu数

//        Singleton7.INSTANCE;
    }

}


