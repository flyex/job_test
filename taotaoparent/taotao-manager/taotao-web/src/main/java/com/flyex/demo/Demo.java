package com.flyex.demo;

import com.flyex.pojo.Student;
import com.flyex.service.StudentService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import java.util.List;

public class Demo {
    public static void main(String[] args) {
        ApplicationContext context = new
                FileSystemXmlApplicationContext("D:\\project\\taotaoparent\\taotao-manager\\taotao-web\\src\\main\\resources\\spring\\applicationContext-dao.xml");
        StudentService studentService = (StudentService)context.getBean("studentService");
        /**PageHelper.startPage(1,3);
        List<Student> list = studentService.findAll();
        PageInfo<Student> pageInfo = new PageInfo<>(list);
        long total = pageInfo.getTotal();
        int nextPage = pageInfo.getNextPage();
        System.out.println(total);
        System.out.println(nextPage);
        List<Student> list1 = pageInfo.getList();
        System.out.println(list1);**/
        System.out.println(studentService.findByRowBounds(1,3));

    }
}
