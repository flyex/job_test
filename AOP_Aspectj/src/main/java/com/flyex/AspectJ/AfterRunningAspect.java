package com.flyex.AspectJ;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

@Aspect
public class AfterRunningAspect {
    @AfterReturning(
            pointcut = "execution(* com.flyex.services.Custom.buy(..))",
            returning = "result")
    public void afterRunning(JoinPoint joinPoint,Object result){
        System.out.println("AfterRunning is running");
        System.out.println("拦截的方法名是："+joinPoint.getSignature().getName());
        System.out.println("方法返回的值是："+result);
        System.out.println("*********************");
    }
}
