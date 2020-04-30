package com.haiyang.spring.server.impl;/**
 * @Author: HaiYang
 * @Date: 2020/4/27 9:44
 */

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.haiyang.spring.entity.UserMsg;
import com.haiyang.spring.mapper.UserMsgMapper;
import com.haiyang.spring.server.UserMsgServer;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * @author: Administrator
 * @Date: 2020/4/27 09:44
 * @Description:
 */
@Service
public class UserMsgServerImpl implements UserMsgServer {

    @Resource
    UserMsgMapper userMsgMapper;

    @Override
    public boolean saveBatch(Collection<UserMsg> entityList, int batchSize) {
        return false;
    }

    @Override
    public boolean saveOrUpdateBatch(Collection<UserMsg> entityList, int batchSize) {
        return false;
    }

    @Override
    public boolean updateBatchById(Collection<UserMsg> entityList, int batchSize) {
        return false;
    }

    @Override
    public boolean saveOrUpdate(UserMsg entity) {
        return false;
    }

    @Override
    public UserMsg getOne(Wrapper<UserMsg> queryWrapper, boolean throwEx) {
        return null;
    }

    @Override
    public Map<String, Object> getMap(Wrapper<UserMsg> queryWrapper) {
        return null;
    }

    @Override
    public <V> V getObj(Wrapper<UserMsg> queryWrapper, Function<? super Object, V> mapper) {
        return null;
    }

    @Override
    public List<UserMsg> list() {
        return userMsgMapper.selectList(null);
    }

    @Override
    public BaseMapper<UserMsg> getBaseMapper() {
        return null;
    }
}
