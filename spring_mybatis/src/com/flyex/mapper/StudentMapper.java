package com.flyex.mapper;

import com.flyex.bean.Student;

import java.util.List;

public interface StudentMapper {
    public List<Student> findAll();
    public void update(Student student);
    public void insert(Student student);
    public Student findById(int id);
    public void delete(int id);
}
