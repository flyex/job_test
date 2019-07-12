package com.flyex.go.user.dao;

import com.flyex.go.user.bean.Student;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import java.util.List;

public class StudentDao extends HibernateDaoSupport {
    public List<Student> findAll(){
        String hql = "from Student";
        List<Student> studentList = (List<Student>) getHibernateTemplate().find(hql);
        if (studentList!=null&&studentList.size()>0){
            return studentList;
        }
        return null;
    }

    public Student findStudent(Student student) {
        String hql = "from Student where username=:name and password=:pwd";
        List<Student> studentList =
                (List<Student>) getHibernateTemplate().
                        findByNamedParam(hql,new String[]{"name","pwd"},new String[]{student.getUsername(),student.getPassword()});
        if (studentList!=null&&studentList.size()>0){
            return studentList.get(0);
        }
        return null;
    }
}
