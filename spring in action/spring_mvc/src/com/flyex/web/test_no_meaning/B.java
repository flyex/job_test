package com.flyex.web.test_no_meaning;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("b")
//@Scope("prototype")
public class B {
    @Value("#{a}")
     A a;
    @Value("#{a.name}")
     String name;

    public void printname(){
        System.out.println(name);
    }

}
