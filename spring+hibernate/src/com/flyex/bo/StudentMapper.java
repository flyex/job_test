package com.flyex.bo;

import com.flyex.pojo.Student;
//用来存储项目业务功能，不参与数据库curd操作
public interface StudentMapper {
    void sava(Student student);
    void delete(int id);
    void update(Student student);
    Student findStudentById(int id);
}
