package com.haiyang.spring;

import com.haiyang.spring.config.BaseConfig;
import com.haiyang.spring.entity.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;
import java.util.Map;

@SpringBootTest
class SpringbootAnnotationApplicationTests {

    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(BaseConfig.class);

    @Test
    void contextLoads() {
    }

    private void printBeans(AnnotationConfigApplicationContext context){
        String[] names = context.getBeanDefinitionNames();
        for (String name : names) {
            System.out.println("beanName = " + name);
        }
    }

    @Test
    void  testBean(){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(BaseConfig.class);
        Person person = (Person) context.getBean("person");
        System.out.println("person = " + person);
    }

    @Test
    void testBeanDefinotion(){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(BaseConfig.class);
        BeanDefinition definition = context.getBeanDefinition("person");
        System.out.println("person.definition = " + definition);
    }
    
    @Test
    public void testAnnotation(){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(BaseConfig.class);
        String[] names = context.getBeanDefinitionNames();
        Arrays.stream(names).forEach(bean-> System.out.println("bean = " + bean));
    }

    @Test
    void  testScope(){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(BaseConfig.class);
        Person person = (Person) context.getBean("person");
        Person person2 = (Person) context.getBean("person");
        System.out.println("person = " + (person==person2));
    }

    @Test
    void  testConditional(){
        String[] beanNamesForType = context.getBeanNamesForType(Person.class);
        Arrays.stream(beanNamesForType).forEach(beanName-> System.out.println("beanName = " + beanName));
        Map<String, Person> beansOfType = context.getBeansOfType(Person.class);
        beansOfType.forEach((k,v)-> System.out.println("beanName = " + k + " , bean = " + v));

    }

    @Test
    void  testImport(){
        printBeans(context);
    }

    @Test
    void  testFactoryBean(){
        Object catFactoryBean = context.getBean("catFactoryBean");
        //catFactoryBean.getClass() = class com.haiyang.spring.entity.Cat
        System.out.println("catFactoryBean.getClass() = " + catFactoryBean.getClass());
        Object beanById = context.getBean("&catFactoryBean");
        //beanById.getClass() = class com.haiyang.spring.config.CatFactoryBean
        System.out.println("beanById.getClass() = " + beanById.getClass());
    }

    @Test
    void  testBeanLifeCircle(){
        printBeans(context);
//        context.getBean("dog");
    }


}
