package com.flyex.new_springjdbc.dao.impl;

import com.flyex.new_springjdbc.bean.Student;
import com.flyex.new_springjdbc.dao.StudentDao;

import javax.sql.DataSource;
import java.sql.*;

public class StudentDaoImpl implements StudentDao {
    private DataSource dataSource;
    public void setDataSource(DataSource dataSource){
        this.dataSource = dataSource;
    }
    @Override
    public void add(Student student) {
        String sql ="insert into students (id,name,birth) values (?,?,?)";
        Connection connection = null;
        PreparedStatement preparedStatement = null;

    try{
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,student.getId());
            preparedStatement.setString(2,student.getName());
            java.sql.Date date = new java.sql.Date(student.getBirth().getTime());
            preparedStatement.setDate(3,date);

            preparedStatement.executeUpdate();


        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public Student findById(int id) {
        String sql = "select id,name,birth from students where id = ?";
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            conn = dataSource.getConnection();
            statement = conn.prepareStatement(sql);
            statement.setInt(1,id);
            resultSet = statement.executeQuery();
            Student student =null;
            if(resultSet.next()){
                student = new Student();
                student.setId(resultSet.getInt("id"));
                student.setName(resultSet.getString("name"));
                student.setBirth(resultSet.getDate("birth"));
            }
            return student;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void delete(int id) {
        String sql = "delete from students where id = ?";
        PreparedStatement statement = null;
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            statement = conn.prepareStatement(sql);
            statement.setInt(1,id);
            statement.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void update(Student student,int id) {
        String sql = "update students set id = ?,name = ?,birth = ?"+
                "where id = ?";
        PreparedStatement statement = null;
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            statement = conn.prepareStatement(sql);
            statement.setInt(1,student.getId());
            statement.setString(2,student.getName());
            Date date = new Date(student.getBirth().getTime());
            statement.setDate(3,date);
            statement.setInt(4,id);
            statement.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
