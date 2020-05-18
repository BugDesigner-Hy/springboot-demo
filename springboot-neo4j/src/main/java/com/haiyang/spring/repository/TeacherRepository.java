package com.haiyang.spring.repository;/**
 * @Author: HaiYang
 * @Date: 2020/5/15 11:59
 */

import com.haiyang.spring.entity.Teacher;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

/**
 * @author: Administrator
 * @Date: 2020/5/15 11:59
 * @Description:
 */
@Repository
public interface TeacherRepository extends Neo4jRepository<Teacher,Long> {
}
