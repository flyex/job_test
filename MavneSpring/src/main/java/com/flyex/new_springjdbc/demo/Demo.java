package com.flyex.new_springjdbc.demo;

import com.flyex.new_springjdbc.bean.Student;
import com.flyex.new_springjdbc.dao.impl.StudentDaoImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Demo {
    public static void main(String[] args)  {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-module.xml");
        StudentDaoImpl studentDao = (StudentDaoImpl)context.getBean("studentDaoImpl");

        try {
            Student student = new Student();
            student.setId(11);
            student.setName("小李");
            DateFormat dateFormat = new SimpleDateFormat("yyyy年MM月dd日", Locale.CHINA);
            Date date = dateFormat.parse("2019年08月30日");

            student.setBirth(date);

            studentDao.add(student);

            Student s = studentDao.findById(10);
            System.out.printf("姓名:%S\nid:%s\n生日:%s\n",s.getName(),s.getId(),s.getBirth());

            studentDao.delete(3);

            studentDao.update(student,2);
        }catch (ParseException e){
            e.printStackTrace();
        }
    }
}
