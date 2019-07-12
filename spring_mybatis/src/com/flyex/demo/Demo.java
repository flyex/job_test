package com.flyex.demo;

import com.flyex.bean.Student;
import com.flyex.service.StudentService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Demo {
    public static void main(String[] args)throws Exception{
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-mybatis.xml");
        StudentService studentService = (StudentService)context.getBean("studentService");
        List<Student> sList = studentService.findAll();
        //List<Student> sList2 = studentService.findAll();

        //for (Student s:sList){
        //    System.out.println(s);
        //}
        //insert student
        System.out.println(sList+"\n");
       /** SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date date = sdf.parse(sdf.format(new Date()));
        Timestamp timestamp = new Timestamp(date.getTime());
        Student student = new Student("小华",13,timestamp,"兰谷路200号");**/
        //studentService.insert(student);



        //find and update student
        /**Student student1 = studentService.findById(5);
        System.out.println(sList);
        student1.setName("小子");
        studentService.update(student1);**/

        //delete
        //studentService.delete(5);
    }
}
