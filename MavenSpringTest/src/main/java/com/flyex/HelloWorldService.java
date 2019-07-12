package com.flyex;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class HelloWorldService {
    private HelloWorld helloWorld;
    String message;

    public void setMessage(String message) {
        this.message = message;
    }

    public HelloWorldService() {}

    @Autowired
    @Qualifier("strutsHelloWorld")
    public void setHelloWorld(HelloWorld helloWorld) {
        this.helloWorld = helloWorld;
    }

    public HelloWorld getHelloWorld() {
        return this.helloWorld;
    }

    public void go(){
        helloWorld.sayHello();
    }
    public void say(){System.out.println(message);}
}
