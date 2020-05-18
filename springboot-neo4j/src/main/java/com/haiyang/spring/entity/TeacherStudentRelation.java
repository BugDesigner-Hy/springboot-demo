package com.haiyang.spring.entity;/**
 * @Author: HaiYang
 * @Date: 2020/5/15 11:57
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.neo4j.ogm.annotation.*;

import java.util.List;

/**
 * @author: Administrator
 * @Date: 2020/5/15 11:57
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@RelationshipEntity(type = "teach")
public class TeacherStudentRelation {

    @Id
    @GeneratedValue
    private Long id;

    @StartNode
    private Teacher teacher;

    @EndNode
    private Student student;

    public TeacherStudentRelation(Teacher teacher, Student student) {
        this.teacher = teacher;
        this.student = student;
    }
}
