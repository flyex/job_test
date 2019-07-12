package com.flyex.web.test_no_meaning;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class A_Bdemo {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(A_Bconfig.class);
        B b = (B)context.getBean("b");
        b.printname();
    }
}
