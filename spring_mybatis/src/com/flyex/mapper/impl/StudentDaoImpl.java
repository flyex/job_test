package com.flyex.mapper.impl;

import com.flyex.bean.Student;
import com.flyex.mapper.StudentMapper;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class StudentDaoImpl /**implements StudentMapper**/ {
   /** private SqlSessionTemplate sqlSessionTemplate;

    public SqlSessionTemplate getSqlSessionTemplate() {
        return sqlSessionTemplate;
    }

    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        this.sqlSessionTemplate = sqlSessionTemplate;
    }

    @Override
    public List<Student> findAll() {
        return sqlSessionTemplate.selectList(
                "com.flyex.mapper.StudentMapper.findAll");
    }

    @Override
    public void update(Student student) {
        sqlSessionTemplate.update("com.flyex.mapper.StudentMapper.update",student);
    }

    @Override
    public void insert(Student student) {
        sqlSessionTemplate.insert("com.flyex.mapper.StudentMapper.insert",student);
    }

    @Override
    public Student findById(int id) {
        return sqlSessionTemplate.selectOne("com.flyex.mapper.StudentMapper.findById",id);
    }

    @Override
    public void delete(int id) {
        sqlSessionTemplate.delete("com.flyex.mapper.StudentMapper.delete",id);
    }**/
}
