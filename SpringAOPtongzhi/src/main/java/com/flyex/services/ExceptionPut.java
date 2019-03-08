package com.flyex.services;

import org.springframework.aop.ThrowsAdvice;

public class ExceptionPut implements ThrowsAdvice {
    public void afterThrowing(IllegalArgumentException e) throws Throwable{
        System.out.println("获取异常后抛出：");
    }
}
