package com.haiyang.spring.controller;/**
 * @Author: HaiYang
 * @Date: 2020/5/15 13:22
 */

import com.haiyang.spring.entity.Student;
import com.haiyang.spring.entity.Teacher;
import com.haiyang.spring.repository.StudentRepository;
import com.haiyang.spring.repository.TeacherRepository;
import com.haiyang.spring.repository.TeacherStudentRelationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author: Administrator
 * @Date: 2020/5/15 13:22
 * @Description:
 */
@Slf4j
@RestController
public class Neo4jController {

    @Resource
    TeacherRepository teacherRepository;

    /**
     * 访问http://localhost:7474/browser/查看图关系
     * @return
     */
    @GetMapping("add")
    public String add() {
        Teacher teacher = new Teacher("teacher1", 30);
        for (int i = 1; i < 6; i++) {
            Student student = new Student("student" + i, 10 + i);
            teacher.addRelation(student);
        }
        teacherRepository.save(teacher);
        return "add success";
    }

    @GetMapping("find")
    public void find() {
        Iterable<Teacher> all = teacherRepository.findAll();
        all.forEach(teacher -> log.info(teacher.toString()));
    }
}
