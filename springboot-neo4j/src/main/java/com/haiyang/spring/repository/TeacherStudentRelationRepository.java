package com.haiyang.spring.repository;

import com.haiyang.spring.entity.TeacherStudentRelation;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author: HaiYang
 * @Date: 2020/5/15 13:21
 */
@Repository
public interface TeacherStudentRelationRepository extends Neo4jRepository<TeacherStudentRelation,Long> {
}
