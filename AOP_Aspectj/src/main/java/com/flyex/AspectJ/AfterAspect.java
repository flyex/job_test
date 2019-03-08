package com.flyex.AspectJ;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class AfterAspect {

    @After("execution(* com.flyex.services.Custom.goShopping(..))")
    public void after(JoinPoint joinPoint){
        System.out.println("Aspect is running");
        System.out.println("拦截到的方法是："+joinPoint.getSignature().getName());
        System.out.println("***************");
    }
}
