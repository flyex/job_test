package com.flyex.service;

import com.flyex.model.Student;

import java.util.List;

public interface StudentService {
    public void save(Student student);
    public boolean update(Student user);
    public boolean delete(int id);
    public Student findById(int id);
    public List<Student> findAll();
}
