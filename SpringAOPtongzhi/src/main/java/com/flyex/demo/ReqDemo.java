package com.flyex.demo;

import com.flyex.services.RequestService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class ReqDemo {
    public static void main(String[] args) {
        /**String b = "a";
        Set a = new HashSet();
        a.add("a");
        a.add("a");
        a.add(b);
        a.add(b);
        Iterator itr = a.iterator();
        while(itr.hasNext()){
            System.out.println(itr.next());
        }**/
        ApplicationContext context = new ClassPathXmlApplicationContext("test.xml");
        RequestService gg = (RequestService) context.getBean("requestServices");
        gg.printName();
        gg.printURL();
        try {
            gg.printThrowException();
        }catch (Exception e){}
    }
}
