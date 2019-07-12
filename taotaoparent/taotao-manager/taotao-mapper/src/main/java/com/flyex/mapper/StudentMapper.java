package com.flyex.mapper;

import com.flyex.pojo.Student;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface StudentMapper {
    List<Student> findAll();
    Student findById(int id);
    List<Student> findByRowBounds(RowBounds rowBounds);
}