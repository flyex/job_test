package com.new_fleyex.aspect;

import org.aspectj.lang.ProceedingJoinPoint;

public class Aspect_inXML {
    public void seatDown(){
        System.out.println("everybody seat down!");
    }

    public void silence(){
        System.out.println("silence your phone");
    }

    public void applause(){
        System.out.println("good good!");
    }

    public void failed(){
        System.out.println("perform is failed");
    }

    public void aroundPerform(ProceedingJoinPoint joinPoint){
        try{
            System.out.println("0everybody seat down!");
            System.out.println("silence your phone");
            joinPoint.proceed();
            System.out.println("good good!");
        }catch (Throwable t){
            System.out.println("perform is failed");
        }
    }
}
