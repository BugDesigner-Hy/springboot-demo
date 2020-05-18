package com.haiyang.spring.entity;/**
 * @Author: HaiYang
 * @Date: 2020/5/15 11:56
 */

import lombok.*;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@NodeEntity(label = "teacher")
public class Teacher {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private int age;

    @Relationship(type = "teach",direction = Relationship.OUTGOING)
    private Set<TeacherStudentRelation> relationSet = new HashSet<>();

    public Teacher(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void addRelation(Student student){
        TeacherStudentRelation relation = new TeacherStudentRelation(this, student);
        student.getRelationSet().add(relation);
        relationSet.add(relation);
    }
}
