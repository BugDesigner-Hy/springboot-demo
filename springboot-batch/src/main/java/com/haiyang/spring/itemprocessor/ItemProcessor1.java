package com.haiyang.spring.itemprocessor;/**
 * @Author: HaiYang
 * @Date: 2020/5/8 17:39
 */

import com.haiyang.spring.entity.User;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

/**
 * @author: Administrator
 * @Date: 2020/5/8 17:39
 * @Description:
 */
@Component
public class ItemProcessor1 implements ItemProcessor<User, User> {
    @Override
    public User process(User user) {
        return new User(user.getId(), user.getName().toUpperCase(), user.getAge(), user.getEmail());
    }
}
