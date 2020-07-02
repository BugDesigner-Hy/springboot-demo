package com.haiyang.spring.config;
/**
 * @Author: HaiYang
 * @Date: 2020/6/12 17:11
 */

import com.haiyang.spring.entity.Car;
import com.haiyang.spring.entity.Color;
import com.haiyang.spring.entity.Person;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.Controller;

@Configuration
//@Conditional(value = {CustomBeanCondition.class})
//@ComponentScan(value = "com.haiyang.spring",excludeFilters = {
//        @ComponentScan.Filter(type = FilterType.ANNOTATION,classes = {Controller.class})
//})
@ComponentScan(value = {"com.haiyang.spring.entity","com.haiyang.spring.config"})
@Import({Color.class,MyImportSelector.class,MyImportBeanDefinitionRegistrar.class})
public class BaseConfig {

    /**
     * @Scope
     * prototype 多例  IOC容器启动并不会去调用方法创建对象放在容器 获取的时候
     * singleton 默认 单例 单例在IOC容器初始化时创建好了
     * @Lazy  懒加载 容器启动不创建对象 第一次使用的时候创建
     * @return
     */
    @Bean("person")
    @Scope("singleton")
    @Lazy
    public Person person() {
        return new Person("haiyang", 23);
    }

    /**
     * @Conditional 按照条件进行判断 满足条件给容器an
     * 也可以放到类上 满足条件 类里面配置的所有bean才会生效
     * @return
     */
    @Conditional(value = {CustomBeanCondition.class})
    @Bean
    public Person person01() {
        return new Person("001", 24);
    }

    @Bean
    public Person person02() {
        return new Person("002", 23);
    }

    /**
     * 给容器中注册组件
     * 1.包扫描 + 组件注解（@Controller等）
     * 2.@Bean[导入的第三方包里面的组件]
     * 3.@Import[快速导入组件] beanName默认是全类名 beanName = com.haiyang.spring.entity.Color
     *      1).@Import({Color.class})
     *      2).实现importSelector接口
     *      3).实现ImportBeanDefinitionRegistrar接口
     *4.使用Spring提供的FactoryBean(工厂bean)
     *      1)默认获取的是调用getObject()创建的对象
     *      2）要获取工程bean本身，需要给Id前加 & 标识 详见测试
     */
    @Bean
    public CatFactoryBean catFactoryBean(){
        return new CatFactoryBean();
    }

    /**
     * bean的生命周期：bean的创建-初始化-销毁的过程
     * 容器管理bean的生命周期
     *
     * 我们可以自定义初始化和销毁方法：
     * 1）xml配置  init-method  destory-method
     * 2)注解方式
     * 3)让Bean实现InitializingBean接口定义初始化逻辑  实现DisposableBean接口实现销毁方法
     * 4)使用JSR250规范注解 @PostConstruct 在bean创建完成并属性赋值完成后执行
     *   @PreDestroy 在容器销毁Bean之前做清理工作
     * 5)实现BeanPostProcessor接口 :bean的后置处理器 在bean初始化前后进行处理工作
     *
     */
    @Bean(initMethod = "init",destroyMethod = "destroy")
    public Car car(){
        return new Car();
    }

    /**
     * @Profile
     * @Autowired @Qualifier 按照类型
     * @Resources 按照属性名称
     * 实现xxxAware  可以注入spring底层的组件
     */

    /**
     * AOP 面向切面编程 ****动态代理
     *
     */

    /**
     * 声明式事务
     * ****后置处理器
     */
}
