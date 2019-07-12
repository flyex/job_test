package com.flyex;

import org.springframework.stereotype.Component;

@Component
public class SpringHelloWorld implements HelloWorld {
    public void sayHello() {
        System.out.println("Spring Say Hello!!");
    }
}
