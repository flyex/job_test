package com.flyex.go.user.service;


import com.flyex.go.user.bean.Student;
import com.flyex.go.user.dao.StudentDao;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class StudentService {
    private StudentDao studentDao;

    public StudentDao getStudentDao() {
        return studentDao;
    }

    public void setStudentDao(StudentDao studentDao) {
        this.studentDao = studentDao;
    }


    public Student findStudent(Student student) {
        return studentDao.findStudent(student);
    }
}
