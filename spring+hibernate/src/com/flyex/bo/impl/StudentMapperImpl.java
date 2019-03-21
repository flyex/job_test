package com.flyex.bo.impl;

import com.flyex.bo.StudentMapper;
import com.flyex.dao.StudentDao;
import com.flyex.pojo.Student;

public class StudentMapperImpl implements StudentMapper {
    private StudentDao studentDao;

    public void setStudentDao(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    @Override
    public void sava(Student student) {
        studentDao.sava(student);
    }

    @Override
    public void delete(int id) {
        studentDao.delete(id);
    }

    @Override
    public void update(Student student) {
        studentDao.update(student);
    }

    @Override
    public Student findStudentById(int id) {
        return studentDao.findStudentById(id);
    }
}
