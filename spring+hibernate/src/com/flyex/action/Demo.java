package com.flyex.action;

import com.flyex.bo.StudentMapper;
import com.flyex.pojo.Student;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Demo {
    public static void main(String[] args) throws Exception {
        ApplicationContext context = new FileSystemXmlApplicationContext("D:/project/spring+hibernate/resources/config/sping-hibernate-config.xml");
        StudentMapper student = (StudentMapper)context.getBean("studentImpl");
        Student student1 =new Student();
        student1.setId(110);
        student1.setName("会会");
        SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd");
        String date = "2019-10-10";
        Date date2 = d.parse(date);
        student1.setDate(date2);

        student.sava(student1);

        //student.findStudentById(3);
    }
}
