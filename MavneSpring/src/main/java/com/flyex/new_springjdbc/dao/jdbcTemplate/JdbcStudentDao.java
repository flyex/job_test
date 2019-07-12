package com.flyex.new_springjdbc.dao.jdbcTemplate;

import com.flyex.new_springjdbc.bean.Student;
import com.flyex.new_springjdbc.dao.StudentDao;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class JdbcStudentDao implements StudentDao {
    private JdbcTemplate jdbcTemplate;
    private DataSource dataSource;

    public void setDataSource(DataSource dataSource){
        this.dataSource = dataSource;
    }
    @Override
    public void add(Student student) {
        String jsql = "insert into students (id,name,birth) values (?,?,?)";
        jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.update(jsql,new Object[]{student.getId(),
                        student.getName(),student.getBirth()});
    }

    @Override
    public Student findById(int id) {
        String jsql = "select * from students where id = ? ";
        jdbcTemplate = new JdbcTemplate(dataSource);
        Student student = (Student)jdbcTemplate.queryForObject(jsql,new Object[]{id},
                    new BeanPropertyRowMapper(Student.class));
        return student;
    }

    @Override
    public void delete(int id) {
        String sql = "delete from students where id = ?";
        jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.update(sql,new Object[]{id});
    }

    @Override
    public void update(Student student, int id) {
        //类似add delete方法
    }
    //jdbcTemplate.queryForList 查询结果集
    public List<String> findNmaeById(int id){
        String sql = "select name from students where id = ?";
        jdbcTemplate = new JdbcTemplate(dataSource);
        List<String> names = jdbcTemplate.queryForList(sql,new Object[]{id},String.class);
        return names;
    }

    //执行插入一批sql语句
    public void addBatch(final List<Student> students){
        /**jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.batchUpdate(strings);**/
        String sql = "insert into students (id,name,birth) values (?,?,?)";

        jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                Student student = students.get(i);
                ps.setInt(1,student.getId());
                ps.setString(2,student.getName());
                Date date = new Date(student.getBirth().getTime());
                ps.setDate(3,date);
            }

            @Override
            public int getBatchSize() {
                return students.size();
            }
        });
    }
}
