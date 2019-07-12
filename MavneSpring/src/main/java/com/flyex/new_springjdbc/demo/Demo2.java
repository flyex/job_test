package com.flyex.new_springjdbc.demo;

import com.flyex.new_springjdbc.bean.Student;
import com.flyex.new_springjdbc.dao.jdbcTemplate.JdbcStudentDao;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Demo2 {
    public static void main(String[] args) throws Exception{
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-module.xml");
        JdbcStudentDao studentDao = (JdbcStudentDao)context.getBean("jdbcBean");
        Student student = new Student();
        //添加学生
       /** student.setId(1);

        student.setName("小long");

        DateFormat dateFormat = new SimpleDateFormat("yyyy年MM月dd日", Locale.CHINA);
        Date date = dateFormat.parse("1994年08月30日");
        student.setBirth(date);

        studentDao.add(student);**/

        Student student2 = studentDao.findById(10);
        System.out.printf("id:%s\nname:%s\nbirth:%s\n",student2.getId(),
                student2.getName(),student2.getBirth());

        List<String> list =studentDao.findNmaeById(11);
        for(String name:list){
            System.out.println(name);
        }

       // studentDao.delete(1);

        /**String[] sqls = {"insert into students (id,name,birth) values (1,'垄崽','1991-09-11')",
                "insert into students (id,name,birth) values (2,'毁在','1991-06-11')",
                "insert into students (id,name,birth) values (3,'狗仔','1991-11-11')"};
        studentDao.addBatch(sqls);**/
        List<Student> list2 = new ArrayList<Student>();
        Student student1 = new Student();
        student1.setId(1);
        student1.setName("花花");
        student1.setBirth(new SimpleDateFormat("yyyy年MM月dd日", Locale.CHINA).parse("1991年03月11日"));
        Student student3 = new Student();
        student3.setId(2);
        student3.setName("琴琴1");
        student3.setBirth(new SimpleDateFormat("yyyy年MM月dd日", Locale.CHINA).parse("1991年03月11日"));

        list2.add(student1);
        list2.add(student3);

        studentDao.addBatch(list2);
    }
}
