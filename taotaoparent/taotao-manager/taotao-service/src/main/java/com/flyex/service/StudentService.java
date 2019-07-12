package com.flyex.service;

import com.flyex.pojo.Student;

import java.util.List;

public interface StudentService {

    List<Student> findAll();
    Student findById(int id);
    List<Student> showByPage(int page);
    List<Student> findByRowBounds(int start,int limit);
}
