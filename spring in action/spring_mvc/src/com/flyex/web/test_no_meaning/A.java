package com.flyex.web.test_no_meaning;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("a")
//@Scope("prototype")
public class A {
    @Value("啊哈")
    public String name;
    @Value("12")
    public int age;
}
