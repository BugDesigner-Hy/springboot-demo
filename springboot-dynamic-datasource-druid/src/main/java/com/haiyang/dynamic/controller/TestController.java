package com.haiyang.dynamic.controller;

/**
 * @Author: HaiYang
 * @Date: 2020/11/9 10:24
 */

import cn.hutool.http.HttpStatus;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.haiyang.dynamic.entity.DriverInfo;
import com.haiyang.dynamic.service.impl.DriverInfoServiceImpl;
import com.haiyang.dynamic.util.ResponseWrapper;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import javax.annotation.Resource;
import java.util.List;

/**
 * @author: Administrator
 * @Date: 2020/11/9 10:24
 * @Description:
 */
@Slf4j
@RestController
@RequestMapping("/v1")
@Api(value = "TestController", tags = "test")
public class TestController {

    @Resource
    DriverInfoServiceImpl driverInfoService;

    @GetMapping("test1")
    public R test1() {
        Page<DriverInfo> page = new Page<>(1,5);
        QueryWrapper<DriverInfo> wrapper = new QueryWrapper<DriverInfo>().eq("city", "西安市")
                .select("driver_id","driver_name");
        Page<DriverInfo> infoPage = driverInfoService.page(page, wrapper);
        return R.ok(infoPage).setMsg("success").setCode(HttpStatus.HTTP_OK);
    }

    @PostMapping("/test2")
    public R test2(String driverId){
        DriverInfo oneByDriverId = driverInfoService.getOneByDriverId(driverId);
        return R.ok(oneByDriverId).setCode(HttpStatus.HTTP_OK);
    }

    @PostMapping("/test3")
    public R test3(String driverId){
        QueryWrapper<DriverInfo> wrapper = new QueryWrapper<DriverInfo>().eq("city", "西安市")
                .select("driver_id","driver_name").last("limit 2");
        List<DriverInfo> allByCity = driverInfoService.getAllByCity(wrapper);
        return R.ok(allByCity).setCode(HttpStatus.HTTP_OK);
    }

    @PostMapping("/test4")
    public R test4(String city){
        Page<DriverInfo> page = new Page<>(1,5);
        QueryWrapper<DriverInfo> wrapper = new QueryWrapper<DriverInfo>().eq("city", city)
                .select("driver_id","driver_name");
        Page<DriverInfo> pageByCity = driverInfoService.getPageByCity(page,wrapper);
        return R.ok(pageByCity).setCode(HttpStatus.HTTP_OK);
    }
}
