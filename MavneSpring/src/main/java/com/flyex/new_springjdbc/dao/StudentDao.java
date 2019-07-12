package com.flyex.new_springjdbc.dao;

import com.flyex.new_springjdbc.bean.Student;

public interface StudentDao {
    void add(Student student);
    void delete(int id);
    void update(Student student,int id);
    Student findById(int id);
}
