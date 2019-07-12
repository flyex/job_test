package com.flyex.mapper.impl;

import com.flyex.mapper.StudentMapper;
import com.flyex.pojo.Student;
import org.mybatis.spring.SqlSessionTemplate;

import java.util.List;

public class StudentMapperImpl  {
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
    public Student findById(int id) {
        return sqlSessionTemplate.selectOne(
                "com.flyex.mapper.StudentMapper.findById",id);
    }**/
}
