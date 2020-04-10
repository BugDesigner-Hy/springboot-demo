## spring OAuth2

官网： https://docs.spring.io/spring-security/site/docs/5.3.2.BUILD-SNAPSHOT/reference

1.添加依赖
```xml
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-oauth2-client</artifactId>
        </dependency>
```

2.添加application.yml配置 以github为例
```yaml
spring:
  security:
    oauth2:
      client:
        registration:
          github:
            client-id: xxxxx
            client-secret: xxxxx
            client-name: github
            authorization-grant-type: authorization_code
#            authorization-uri: https://github.com/login/oauth/authorize
#            redirect-uri:  http://localhost:8080/oauth2/callback
#            token-uri: https://github.com/login/oauth/access_token
```
3.新建配置类`OAuth2LoginSecurityConfig`继承`WebSecurityConfigurerAdapter`
```java
@Configuration
@EnableWebSecurity
public class OAuth2LoginSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests(authorize -> authorize
                        .anyRequest().authenticated()
                )
                .oauth2Login(login -> login
                                .userInfoEndpoint(userInfo -> userInfo
                                        .customUserType(GitHubOAuth2User.class, "github"))
                                .defaultSuccessUrl("/login/success")
                )
                .logout(logout -> logout
                        .invalidateHttpSession(true)
                        .clearAuthentication(true)
                        .deleteCookies("JSESSIONID")
                )
                //多端登录下线 只允许一个客户端登录
                .sessionManagement(session -> session.maximumSessions(1));
    }
}
```
4.启动服务 访问localhost:8080/login 跳转到github授权页面 授权成功页面返回login success

5.获取github账户相关信息 详见`OAuthLoginController`
可以通过获取`SecurityContextHolder`上下文来获取`Authentication`
也可以在参数直接注入`@AuthenticationPrincipal GitHubOAuth2User customUser`来获取

6.可以对`@AuthenticationPrincipal`再次封装 使用我们自定义的注解`@CurrentUser`
```java
@Target({ElementType.PARAMETER, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@AuthenticationPrincipal
public @interface CurrentUser {
}
```
Controller
```java
    @ResponseBody
    @GetMapping("/login/success")
    public String loginSuccess(@CurrentUser GitHubOAuth2User currentUser) {
            //...
        return "";
}
```
