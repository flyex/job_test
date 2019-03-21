package com.flyex.services.impl;

import com.flyex.dao.StudentMapper;
import com.flyex.module.Student;
import com.flyex.services.StudentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

public class StudentServiceImpl implements StudentService {

    private StudentMapper studentMapper;

    public StudentServiceImpl(StudentMapper s){
        studentMapper = s;
    }

    @Override
    public void save(Student student) {
        studentMapper.save(student);
    }

    @Override
    public boolean update(Student student) {
        return studentMapper.update(student);
    }

    @Override
    public Student findById(int id) {
        return studentMapper.findById(id);
    }

    @Override
    public boolean delete(int id) {
        return studentMapper.delete(id);
    }

    @Override
    public List<Student> findAll() {
        return studentMapper.findAll();
    }

}
