package com.flyex.dao.impl;

import com.flyex.dao.StudentDao;
import com.flyex.pojo.Student;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Transactional
public class StudentDaoImpl extends HibernateDaoSupport implements StudentDao {
    public void sava(Student student){
        getHibernateTemplate().save(student);
    }
    public void delete(int id){
        getHibernateTemplate().delete(id);
    }
    public void update(Student student){
        getHibernateTemplate().update(student);
    }
    public Student findStudentById(int id){
        List list = getHibernateTemplate().find(
                "from Student where Student.id=? ",id);
        return (Student)list.get(0);
    }

}
