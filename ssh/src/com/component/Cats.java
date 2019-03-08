package com.component;

import org.springframework.stereotype.Component;

@Component("cats")
public class Cats implements animals {
    public void bark(){
        System.out.println("猫喵喵");
    }
}
