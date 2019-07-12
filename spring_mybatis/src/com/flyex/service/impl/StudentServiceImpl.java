package com.flyex.service.impl;

import com.flyex.bean.Student;
import com.flyex.mapper.StudentMapper;
import com.flyex.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component("studentService")
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentMapper studentMapper;

    public StudentMapper getStudentMapper() {
        return studentMapper;
    }

    public void setStudentMapper(StudentMapper studentMapper) {
        this.studentMapper = studentMapper;
    }
    //在spring中同一个事物里进行两次查询才会用到一级缓存
    //@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT)
    @Override
    public List<Student> findAll() {
        studentMapper.findAll();
        return studentMapper.findAll();
    }

    @Override
    public void update(Student student) {
        studentMapper.update(student);
    }

    @Override
    public void insert(Student student) {
        studentMapper.insert(student);
    }

    @Override
    public Student findById(int id) {
        return studentMapper.findById(id);
    }

    @Override
    public void delete(int id) {
        studentMapper.delete(id);
    }
}
