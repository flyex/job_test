package com.flyex.dao;

import com.flyex.pojo.Student;
import org.springframework.transaction.annotation.Transactional;

//实现类扩展了 Spring 的“HibernateDaoSupport”，以使Spring框架支持Hibernate。
@Transactional
public interface StudentDao {
    void sava(Student student);
    void delete(int id);
    void update(Student student);
    Student findStudentById(int id);
}
