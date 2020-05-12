package com.haiyang.spring.controller;/**
 * @Author: HaiYang
 * @Date: 2020/5/12 11:18
 */

import com.haiyang.spring.entity.User;
import com.haiyang.spring.repository.UserRepository;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.CriteriaDefinition;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Optional;

/**
 * @author: Administrator
 * @Date: 2020/5/12 11:18
 * @Description:
 */
@RestController
public class MongoController {

    @Resource
    MongoTemplate mongoTemplate;

    @Resource
    UserRepository userRepository;

    @PostMapping("insert")
    public User insert() {
        User user = new User(2L, "haiyang", 27, new Date());
//        mongoTemplate.insert(user);
        User res = userRepository.insert(user);
        return res;
    }


    @PostMapping("find")
    public User find() {
//        User user = mongoTemplate.findById(1, User.class);
        User byId = userRepository.findOneByName("bsj");
        return byId;
    }

    @PostMapping("update")
    public User update() {
//        Query query = new Query(Criteria.where("name").is("haiyang"));
//        UpdateResult result = mongoTemplate.updateFirst(query, Update.update("name", "bsj"), User.class);
        User user = new User(2L, "bsj", 27, new Date());
        User save = userRepository.save(user);
        return save;
    }

    @PostMapping("del")
    public void del() {
//        User user = User.builder().id(1L).build();
//        DeleteResult remove = mongoTemplate.remove(user);
        userRepository.deleteById(2L);
        return ;
    }

}
