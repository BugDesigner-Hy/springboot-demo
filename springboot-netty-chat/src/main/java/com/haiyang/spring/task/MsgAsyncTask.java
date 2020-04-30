package com.haiyang.spring.task;/**
 * @Author: HaiYang
 * @Date: 2020/4/27 10:22
 */

import com.haiyang.spring.entity.UserMsg;
import com.haiyang.spring.mapper.UserMsgMapper;
import com.haiyang.spring.server.LikeCacheTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.concurrent.Future;

/**
 * @author: Administrator
 * @Date: 2020/4/27 10:22
 * @Description:
 */
@Component
public class MsgAsyncTask {

    @Autowired
    private LikeCacheTemplate cacheTemplate;

    @Autowired
    private UserMsgMapper UserMsgMapper;

    @Async
    @Scheduled(fixedRate = 5000)//单位毫秒
    public Future<Boolean> saveChatMsgTask() throws Exception{
//        System.out.println("启动异步任务");
        Set<UserMsg> set = cacheTemplate.cloneCacheMap();
        for (UserMsg item:set){
            //保存用户消息
            UserMsgMapper.insert(item);
        }
        //清空临时缓存
        cacheTemplate.clearCacheMap();
        return new AsyncResult<>(true);
    }

}
