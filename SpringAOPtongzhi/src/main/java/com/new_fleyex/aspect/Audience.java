package com.new_fleyex.aspect;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

@Aspect
public class Audience {//观众
    /**@Before("execution(* com.new_fleyex.cut_point.Performance.perform(..))")
    public void seatDown(){
        System.out.println("everybody seat down!");
    }
    @Before("execution(* com.new_fleyex.cut_point.Performance.perform(..))")
    public void silence(){
        System.out.println("silence your phone");
    }
    @AfterReturning("execution(* com.new_fleyex.cut_point.Performance.perform(..))")
    public void applause(){
        System.out.println("good good!");
    }
    @AfterThrowing("execution(* com.new_fleyex.cut_point.Performance.perform(..))")
    public void failed(){
        System.out.println("perform is failed");
    }**/
    @Pointcut("execution(* com.new_fleyex.cut_point.Performance.perform(..))")//定义一个切面
    public void performance(){}

    /**@Before("performance()")
    public void seatDown(){
    System.out.println("everybody seat down!");
}
    @Before("performance()")
    public void silence(){
    System.out.println("silence your phone");
    }
    @AfterReturning("performance()")
    public void applause(){
    System.out.println("good good!");
    }
    @AfterThrowing("performance()")
    public void failed(){
    System.out.println("perform is failed");
    }**/

    @Around("performance()")
    public void aroundPerform(ProceedingJoinPoint joinPoint){
        try{
            System.out.println("everybody seat down!");
            System.out.println("silence your phone");
            joinPoint.proceed();
            System.out.println("good good!");
        }catch (Throwable t){
            System.out.println("perform is failed");
        }
    }
}
