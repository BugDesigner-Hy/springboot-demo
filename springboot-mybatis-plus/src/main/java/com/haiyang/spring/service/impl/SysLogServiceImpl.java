package com.haiyang.spring.service.impl;/**
 * @Author: HaiYang
 * @Date: 2020/4/3 15:47
 */

import com.baomidou.dynamic.datasource.annotation.DS;
import com.haiyang.spring.entity.SysLog;
import com.haiyang.spring.mapper.SysLogMapper;
import com.haiyang.spring.service.ISysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: Administrator
 * @Date: 2020/4/3 15:47
 * @Description:
 */
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
