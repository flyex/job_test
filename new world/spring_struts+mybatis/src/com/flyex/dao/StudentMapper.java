package com.flyex.dao;

import com.flyex.model.Student;

import java.util.List;

public interface StudentMapper {
    public void save(Student student);
    public boolean update(Student student);
    public boolean delete(int id);
    public Student findById(int id);
    public List<Student> findAll();
}
