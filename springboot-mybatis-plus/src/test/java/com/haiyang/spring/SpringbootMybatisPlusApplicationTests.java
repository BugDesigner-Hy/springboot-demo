package com.haiyang.spring;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.haiyang.spring.entity.User;
import com.haiyang.spring.mapper.UserMapper;
import com.haiyang.spring.service.ISysLogService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.util.List;

@SpringBootTest
class SpringbootMybatisPlusApplicationTests {

    @Autowired
    UserMapper userMapper;

    @Autowired
    ISysLogService sysLogService;

    @Test
    public void testSelect(){
        List<User> users = userMapper.selectList(null);
        users.forEach(System.out::println);
    }

    @Test
    public void testCustomMapper(){
        String name = userMapper.findNameById(1);
        System.out.println("name = " + name);
    }

    @Test
    public void testQueryWrapper(){
        List<User> users = userMapper.selectList(new QueryWrapper<User>().eq("id","2"));
        users.forEach(System.out::println);
    }

    @Test
    public void testPage(){
        Page<User> users = userMapper.selectPage(new Page<User>(2,3),null);
        users.getRecords().forEach(System.out::println);
    }


    @Test
    public void testDynamicDatasource(){
        sysLogService.getLogs();

        List<User> users = userMapper.selectList(null);
        users.forEach(System.out::println);
    }


}
