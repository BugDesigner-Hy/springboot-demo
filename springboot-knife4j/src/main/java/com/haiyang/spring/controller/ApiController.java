package com.haiyang.spring.controller;/**
 * @Author: HaiYang
 * @Date: 2020/4/3 10:36
 */

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import com.haiyang.spring.util.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: Administrator
 * @Date: 2020/4/3 10:36
 * @Description:
 */

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
