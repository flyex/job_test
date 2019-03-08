package com.component;

import org.springframework.stereotype.Component;

@Component("dogs")
public class Dogs implements animals {
    public void bark(){
        System.out.println("狗汪汪");
    }
}

