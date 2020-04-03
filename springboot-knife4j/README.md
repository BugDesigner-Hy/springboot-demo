##增强版本的Swagger

### Spring Boot单服务架构

官方指南 https://doc.xiaominfo.com/guide/

1. maven中的pom.xml文件引入starter即可
```xml
<dependency>
    <groupId>com.github.xiaoymin</groupId>
    <artifactId>knife4j-spring-boot-starter</artifactId>
    <!--在引用时请在maven中央仓库搜索最新版本号-->
    <version>2.0.2</version>
</dependency>
```
2. 配置类
```java
@Configuration
@EnableSwagger2
@EnableKnife4j
@Import(BeanValidatorPluginsConfiguration.class)
public class SwaggerConfiguration {
    //more...
}
```
3. 编写注解即可
```java
@Api(value = "测试Api",tags = "测试Api")
@ApiSort(1)
@RestController
public class ApiController {

    @ApiOperationSupport(author = "haiyang",order = 1)
    @ApiOperation(value = "获取数据1",notes = "输入name 返回类型为自定义类R",response = R.class)
    @GetMapping("/test1")
    public R getString(String name){
        return R.ok("hell "+name);
    }

    @ApiOperationSupport(author = "haiyang",order = 2)
    @ApiOperation(value = "获取数据2",notes = "输入email 返回类型为自定义类R",response = R.class)
    @GetMapping("/test2")
    public R getString1(String email){
        return R.ok("hell "+email);
    }
}
```
4. 启动服务 访问 http://ip:port/doc.html