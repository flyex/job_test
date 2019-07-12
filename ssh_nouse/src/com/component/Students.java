package com.component;

import org.springframework.stereotype.Component;

@Component("students")
public class Students {

    public void say(){
        System.out.println("我是学生");
    }
}
