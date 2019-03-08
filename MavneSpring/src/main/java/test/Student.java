package test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Student {

    static Person person;

    public static void main(String[] args){
        ApplicationContext context = new ClassPathXmlApplicationContext("test.xml");
        person = (Person)context.getBean("person");
        System.out.println(person.name);
    }
}
