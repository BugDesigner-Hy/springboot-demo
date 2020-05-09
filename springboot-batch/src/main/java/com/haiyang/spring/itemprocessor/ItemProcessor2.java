package com.haiyang.spring.itemprocessor;/**
 * @Author: HaiYang
 * @Date: 2020/5/8 17:40
 */

import com.haiyang.spring.entity.User;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

/**
 * @author: Administrator
 * @Date: 2020/5/8 17:40
 * @Description:
 */
@Component
public class ItemProcessor2 implements ItemProcessor<User, User> {
    @Override
    public User process(User user) {
        if (user.getId() % 2 == 0) {
            return new User(user.getId(), user.getName(), user.getAge(), user.getEmail().toUpperCase());
        } else {
            //过滤掉
            return null;
        }
    }
}
