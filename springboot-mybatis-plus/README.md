##MybatisPlus使用

官方网址 https://mp.baomidou.com/guide

1.引入依赖
```xml
        <!--        引入mybatisplus依赖-->
        <dependency>
            <groupId>com.baomidou</groupId>
            <artifactId>mybatis-plus-boot-starter</artifactId>
            <version>3.3.1.tmp</version>
        </dependency>
        <!--引入mysql驱动-->
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <scope>runtime</scope>
        </dependency>
```

2.启动类添加@MapperScan注解
```java
@SpringBootApplication
@MapperScan("com.haiyang.spring.mapper")
public class SpringbootMybatisPlusApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootMybatisPlusApplication.class, args);
    }

}
```

3.配置类填写数据库相关信息
```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/springboot?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai
    driver-class-name: com.mysql.cj.jdbc.Driver
    username: root
    password: root
```

4.编写实体类
```java
@Data
public class User {

    private int id;
    private String name;
    private String age;
    private String email;
}
```

5.编写Mapper接口
```java
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
```

6.编写测试类进行测试
```java
@SpringBootTest
class SpringbootMybatisPlusApplicationTests {

    @Autowired
    UserMapper userMapper;

    @Test
    public void testSelect(){
        List<User> users = userMapper.selectList(null);
        users.forEach(System.out::println);
    }

}
```

7.代码自动生成 详见com.haiyang.spring.util.CodeGenerator

8.分页插件配置
```java
//Spring boot方式
@EnableTransactionManagement
@Configuration
@MapperScan("com.baomidou.cloud.service.*.mapper*")
public class MybatisPlusConfig {

    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        // 设置请求的页面大于最大页后操作， true调回到首页，false 继续请求  默认false
        // paginationInterceptor.setOverflow(false);
        // 设置最大单页限制数量，默认 500 条，-1 不受限制
        // paginationInterceptor.setLimit(500);
        // 开启 count 的 join 优化,只针对部分 left join
        paginationInterceptor.setCountSqlParser(new JsqlParserCountOptimize(true));
        return paginationInterceptor;
    }
}
```
测试分页效果
```
    @Test
    public void testPage(){
        Page<User> users = userMapper.selectPage(new Page<User>(2,3),null);
        users.getRecords().forEach(System.out::println);
    }
```

9.多数据源配置
添加pom依赖
```xml
        <!--        动态数据源切换-->
        <dependency>
            <groupId>com.baomidou</groupId>
            <artifactId>dynamic-datasource-spring-boot-starter</artifactId>
            <version>3.0.0</version>
        </dependency>
```
配置application.yml
```yaml
#多数据源配置
spring:
  datasource:
    dynamic:
      primary: master #设置默认的数据源或者数据源组,默认值即为master
      datasource:
        master:
          username: root
          password: root
          driver-class-name: com.mysql.cj.jdbc.Driver
          url: jdbc:mysql://localhost:3306/springboot?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai
        slave:
          username: root
          password: root
          driver-class-name: com.mysql.cj.jdbc.Driver
          url: jdbc:mysql://localhost:3306/springboot-slave?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai
```
使用@DS注解切换数据源
@DS 可以注解在方法上和类上，同时存在方法注解优先于类上注解
在mybatis环境下也可注解在mapper接口层
```java
@Mapper
@DS("master") //默认master
public interface UserMapper extends BaseMapper<User> {

    String findNameById();
}
```
注解在service实现上
```java
@Service
@DS("slave")
public class SysLogServiceImpl implements ISysLogService {

    @Autowired
    SysLogMapper sysLogMapper;

    @Override
    public void getLogs(){
        List<SysLog> sysLogs = sysLogMapper.selectList(null);
        sysLogs.forEach(System.out::println);

    }
}
```
测试多数据源
```
    @Test
    public void testDynamicDatasource(){
        sysLogService.getLogs();

        List<User> users = userMapper.selectList(null);
        users.forEach(System.out::println);
    }
```
10.自定义sql方法的实现
在resources下新建mapper目录
新建XXXmapper.xml 可通过代码生成器完成
```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.haiyang.spring.mapper.UserMapper">
    <select id="findNameById" resultType="java.lang.String">
        select name from user where id = #{id}
    </select>
</mapper>
```
在application.yml配置xml扫描路径
```yaml
mybatis-plus:
  mapper-locations: classpath*:/mapper/*.xml
```
测试
```
    @Test
    public void testCustomMapper(){
        String name = userMapper.findNameById(1);
        System.out.println("name = " + name);
    }
```
11.条件构造器使用
```
    @Test
    public void testQueryWrapper(){
        List<User> users = userMapper.selectList(new QueryWrapper<User>().eq("id","2"));
        users.forEach(System.out::println);
    }
```