package com.flyex.AspectJ;

import org.aspectj.lang.JoinPoint;

public class AT {
    public void beforeDo(JoinPoint joinPoint){
        System.out.println("ATTTTTT开始前的准备开始");
        System.out.println("要开始的动作是："+joinPoint.getSignature().getName());
        System.out.println("*************");
    }
}
