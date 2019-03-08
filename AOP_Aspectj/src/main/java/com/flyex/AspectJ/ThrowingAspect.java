package com.flyex.AspectJ;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class ThrowingAspect {

    @AfterThrowing(
            pointcut = "execution(* com.flyex.services.Custom.havaException(..))",
            throwing = "error")
    public void afterThrowing(JoinPoint joinPoint,Throwable error){
        System.out.println("AfterThrowing is running");
        System.out.println("拦截的方法名是："+joinPoint.getSignature().getName());
        System.out.println("异常是："+error);
        System.out.println("********************");
    }
}
