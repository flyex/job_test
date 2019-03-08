package com.flyex.services;

import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

public class BeforeRequest implements MethodBeforeAdvice {   //AOP拦截方法
    public void before(Method method,Object[] args,Object target) throws Throwable{
        System.out.println("方法之前输出:before request");
    }
}
