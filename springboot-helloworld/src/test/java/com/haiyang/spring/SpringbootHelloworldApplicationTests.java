package com.haiyang.spring;

import com.haiyang.spring.bean.Student;
import com.haiyang.spring.config.BaseConfig;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SpringBootTest
class SpringbootHelloworldApplicationTests {


    @Test
    void contextLoads() {
    }

    @Test
    void testa(){
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(BaseConfig.class);
        Student student = (Student) ctx.getBean("student");
        System.out.println("student.getName() = " + student.getName());
    }

}
