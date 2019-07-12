package com.flyex.test;

import com.opensymphony.xwork2.util.finder.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class Demo {
    public static void main(String[] args) {
        ApplicationContext context =
                new FileSystemXmlApplicationContext("D:\\project\\ssh\\web\\WEB-INF\\spring.xml");
        TestSay t = (TestSay)context.getBean("getWord");
        t.say();
    }
}
