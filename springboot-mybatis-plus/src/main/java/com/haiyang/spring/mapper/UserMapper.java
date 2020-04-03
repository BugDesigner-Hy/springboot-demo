package com.haiyang.spring.mapper;/**
 * @Author: HaiYang
 * @Date: 2020/4/3 13:55
 */

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.haiyang.spring.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author: Administrator
 * @Date: 2020/4/3 13:55
 * @Description:
 */
@Mapper
@DS("master") //默认master
public interface UserMapper extends BaseMapper<User> {

    String findNameById(@Param("id") int id);
}
