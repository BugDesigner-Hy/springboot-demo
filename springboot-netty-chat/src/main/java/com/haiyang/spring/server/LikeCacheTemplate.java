package com.haiyang.spring.server;/**
 * @Author: HaiYang
 * @Date: 2020/4/27 10:19
 */

import com.haiyang.spring.entity.UserMsg;
import org.springframework.stereotype.Component;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @author: Administrator
 * @Date: 2020/4/27 10:19
 * @Description:
 */
@Component
public class LikeCacheTemplate {

    private Set<UserMsg> SomeCache = new LinkedHashSet<>();

    public void save(Object user, Object msg) {
        UserMsg userMsg = new UserMsg();
        userMsg.setName(String.valueOf(user));
        userMsg.setMsg(String.valueOf(msg));
        SomeCache.add(userMsg);
    }

    public Set<UserMsg> cloneCacheMap() {
        return SomeCache;
    }

    public void clearCacheMap() {
        SomeCache.clear();
    }
}
