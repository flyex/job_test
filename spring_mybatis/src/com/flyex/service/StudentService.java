package com.flyex.service;

import com.flyex.bean.Student;

import java.util.List;

public interface StudentService {
    List<Student> findAll();
    void update(Student student);
    void insert(Student student);
    Student findById(int id);
    void delete(int id);
}
