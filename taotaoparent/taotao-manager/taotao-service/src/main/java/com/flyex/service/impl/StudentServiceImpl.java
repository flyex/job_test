package com.flyex.service.impl;

import com.flyex.mapper.StudentMapper;
import com.flyex.pojo.Student;
import com.flyex.service.StudentService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("studentService")
public class StudentServiceImpl implements StudentService {
    public StudentMapper getStudentMapper() {
        return studentMapper;
    }

    public void setStudentMapper(StudentMapper studentMapper) {
        this.studentMapper = studentMapper;
    }

    @Autowired
    private StudentMapper studentMapper;

    @Override
    public List<Student> findAll() {
        List<Student> list = studentMapper.findAll();
        if (list!=null&&list.size()>0){
            return list;
        }
        return null;
    }

    @Override
    public Student findById(int id) {
        Student student = studentMapper.findById(id);
        if (student!=null){
            return student;
        }
        return null;
    }

    @Override
    public List<Student> showByPage(int page) {
        PageHelper.startPage(page,3);
        List<Student> list = studentMapper.findAll();
        PageInfo<Student> pageInfo = new PageInfo<>(list);
        return list;
    }

    @Override
    public List<Student> findByRowBounds(int start, int limit) {
        return studentMapper.findByRowBounds(new RowBounds(start,limit));
    }
}
