package com.flyex.category.test;

import com.flyex.category.dao.CategoryDao;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        CategoryDao dao =(CategoryDao) context.getBean("categoryDao");
        System.out.println(dao.findAll());
    }
}
