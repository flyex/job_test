package com.flyex.AspectJ;

import org.aspectj.lang.JoinPoint;

public class ARA {
    public void afterRunning(JoinPoint joinPoint, Object result){
        System.out.println("ARA***** is running");
        System.out.println("拦截的方法名是："+joinPoint.getSignature().getName());
        System.out.println("方法返回的值是："+result);
        System.out.println("*********************");
    }
}
