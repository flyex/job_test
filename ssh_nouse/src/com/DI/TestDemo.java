package com.DI;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class TestDemo {
    public static void main(String[] args){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        Person person = (Person)context.getBean("person");
        System.out.println(person.getName());
        System.out.println(person.getStudents().getName());
        Iterator it = person.getList().iterator();
        while (it.hasNext()){
            System.out.println(it.next());
        }

       Object students2 = person.getList().get(1);
        Students students = (Students) students2;
        System.out.println(students.getName());
    }
}
