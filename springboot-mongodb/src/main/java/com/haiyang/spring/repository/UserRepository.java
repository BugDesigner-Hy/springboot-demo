package com.haiyang.spring.repository;

import com.haiyang.spring.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author: HaiYang
 * @Date: 2020/5/12 11:42
 */
@Repository
public interface UserRepository extends MongoRepository<User,Long> {

    User findOneByName(String name);
}
