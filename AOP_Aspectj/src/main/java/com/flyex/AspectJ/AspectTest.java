package com.flyex.AspectJ;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.JoinPoint;


@Aspect
public class AspectTest {
    @Before("execution(* com.flyex.services.Custom.goShopping(..))")
    public void beforeDo(JoinPoint joinPoint){
        System.out.println("开始前的准备开始");
        System.out.println("要开始的动作是："+joinPoint.getSignature().getName());
        System.out.println("*************");
    }
}
