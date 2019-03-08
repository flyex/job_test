package com.flyex.services;

import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;
import org.springframework.aop.AfterReturningAdvice;

import java.lang.reflect.Method; //AOP拦截方法

public class AfterRequest implements AfterReturningAdvice {
    public void afterReturning(Object returnValue, Method method,Object[] args,Object target) throws Throwable{
        System.out.println("在方法之后输出：after request");
    }
}
