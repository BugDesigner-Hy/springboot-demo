## Spring-security

官网：https://docs.spring.io/spring-security

1.添加依赖
```xml
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-security</artifactId>
		</dependency>
```
2.新建`WebSecurityConfiguration`文件 继承`WebSecurityConfigurerAdapter`

3.新建`CustomUserDetailService` 实现`UserDetailsService`接口

4.可以根据业务情况选择自定义拦截器Filter和处理器Handler

5.想要更精准的控制登录 可实现自定义`AuthenticationProvider`

6.测试 `SpringbootSecurityApplicationTests`

