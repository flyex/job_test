package com.flyex.services;

import com.flyex.module.Student;

import java.util.List;

public interface StudentService {
    public void save(Student student);
    public boolean update(Student student);
    public boolean delete(int id);
    public Student findById(int id);
    public List<Student> findAll();
}
