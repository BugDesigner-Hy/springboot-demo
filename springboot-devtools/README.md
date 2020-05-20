## springboot-devtools
1.添加依赖
```xml
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-devtools</artifactId>
            <scope>runtime</scope>
            <optional>true</optional>
        </dependency>
```
2.添加配置
```yaml
server:
  port: 8080
spring:
  devtools:
    remote:
      restart:
        #热部署生效
        enabled: true
    #设置重启的目录
    restart:
      additional-paths: src/main/java
```
3.修改idea配置
```text
当我们修改了Java类后，IDEA默认是不自动编译的，而spring-boot-devtools又是监测classpath下的文件发生变化才会重启应用，所以需要设置IDEA的自动编译：
   （1）File-Settings-Build-Compiler-Build Project automatically
   （2）ctrl + shift + alt + /,选择Registry,勾上 Compiler autoMake allow when app running
   
```
4.启动项目 修改源码查看热启动效果