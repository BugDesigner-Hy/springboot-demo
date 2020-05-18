package com.haiyang.spring.entity;/**
 * @Author: HaiYang
 * @Date: 2020/5/15 11:55
 */

import lombok.*;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.HashSet;
import java.util.Set;

/**
 * @author: Administrator
 * @Date: 2020/5/15 11:55
 * @Description:
 */

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@NodeEntity(label = "student")
public class Student {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private int age;

    @Relationship(type = "teach",direction = Relationship.INCOMING)
    private Set<TeacherStudentRelation> relationSet = new HashSet<>();

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
