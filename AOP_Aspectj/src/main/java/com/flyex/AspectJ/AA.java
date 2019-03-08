package com.flyex.AspectJ;

import org.aspectj.lang.JoinPoint;

public class AA {
    public void after(JoinPoint joinPoint){
        System.out.println("AAAspect is running");
        System.out.println("拦截到的方法是："+joinPoint.getSignature().getName());
        System.out.println("***************");
    }
}

