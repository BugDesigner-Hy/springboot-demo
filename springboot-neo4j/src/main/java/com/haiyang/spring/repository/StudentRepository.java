package com.haiyang.spring.repository;

import com.haiyang.spring.entity.Student;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author: HaiYang
 * @Date: 2020/5/15 13:20
 */
@Repository
public interface StudentRepository extends Neo4jRepository<Student,Long> {
}
