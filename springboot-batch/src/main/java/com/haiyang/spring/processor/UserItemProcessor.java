package com.haiyang.spring.processor;/**
 * @Author: HaiYang
 * @Date: 2020/5/6 15:57
 */

import com.haiyang.spring.entity.User;
import org.springframework.batch.item.ItemProcessor;

/**
 * @author: Administrator
 * @Date: 2020/5/6 15:57
 * @Description:
 */
public class UserItemProcessor implements ItemProcessor<User,User> {
    @Override
    public User process(User user) throws Exception {
        String profix = "USER-";
        user.setName(profix+user.getName());
        return user;
    }
}
