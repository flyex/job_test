package com.flyex.AspectJ;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.omg.Messaging.SYNC_WITH_TRANSPORT;

import java.util.Arrays;

@Aspect
public class AroundAspect {
    @Around("execution (* com.flyex.services.Custom.addCustom(..))")
    public void aroundRunning(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
        System.out.println("around is running");
        System.out.println("该方法名是："+proceedingJoinPoint.getSignature().getName());
        System.out.println("输入的值是："+ Arrays.toString(proceedingJoinPoint.getArgs()));

        System.out.println("addcustom前执行around");
        proceedingJoinPoint.proceed();
        System.out.println("addcustom后执行around");
System.out.println("******************");
    }
}
