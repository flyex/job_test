package com.flyex;

import org.springframework.stereotype.Component;

@Component
public class StrutsHelloWorld implements HelloWorld {
    public void sayHello() {
        System.out.println("Struts Say Hello!!");
    }
}
