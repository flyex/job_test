package com.flyex.service.impl;

import com.flyex.dao.StudentMapper;
import com.flyex.model.Student;
import com.flyex.service.StudentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service //对应springframework包内
@Transactional   // 此处不再进行创建SqlSession和提交事务，都已交由spring去管理了
public class StudentviceImpl implements StudentService {
    @Resource
    private StudentMapper studentMapper;

    @Override
    public void save(Student student) {
        studentMapper.save(student);
    }

    @Override
    public boolean update(Student student) {
        return studentMapper.update(student);
    }

    @Override
    public Student findById(int id) {
        return studentMapper.findById(id);
    }

    @Override
    public boolean delete(int id) {
        return studentMapper.delete(id);
    }

    @Override
    public List<Student> findAll() {
        return studentMapper.findAll();
    }
}
