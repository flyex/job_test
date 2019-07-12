package com.flyex.web;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;


public class Test {
    int x=1;

    public void setX(int x) {
        this.x = x;
    }

    public void print(){
        System.out.println(x);
    }


}
