package com.haiyang.spring.output.multiple;/**
 * @Author: HaiYang
 * @Date: 2020/5/8 17:17
 */

import com.haiyang.spring.entity.User;
import org.springframework.batch.item.ItemWriter;
import org.springframework.classify.Classifier;

/**
 * @author: Administrator
 * @Date: 2020/5/8 17:17
 * @Description:
 */
public class MyClassifier implements Classifier<User, ItemWriter<? super User>> {

    private ItemWriter<User> itemWriter1;
    private ItemWriter<User> itemWriter2;

    public MyClassifier(ItemWriter<User> itemWriter1, ItemWriter<User> itemWriter2) {

        this.itemWriter1 = itemWriter1;
        this.itemWriter2 = itemWriter2;
    }

    @Override
    public ItemWriter<? super User> classify(User user) {
        return user.getId() % 2 == 0 ? itemWriter1 : itemWriter2;
    }
}
