##springboot-email
1.依赖
```xml
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-mail</artifactId>
        </dependency>
```
2.配置
```yaml
spring:
  mail:
    host: smtp.mxhichina.com
    username: xxx@xxx.co
    password: xxx
#    test-connection: true
```
3.编写MailService

4.测试邮件发送功能