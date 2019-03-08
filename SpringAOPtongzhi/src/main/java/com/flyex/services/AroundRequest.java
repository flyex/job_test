package com.flyex.services;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.util.Arrays;

public class AroundRequest implements MethodInterceptor {
    public Object invoke(MethodInvocation methodInvocation) throws Throwable{
        System.out.println("Method name:"+methodInvocation.getMethod().getName());
        System.out.println("Method agruments:"+ Arrays.toString(methodInvocation.getArguments()));
        System.out.println("before request:获取前输出"); //类似before request
    try{
        Object result = methodInvocation.proceed();
        System.out.println("after request:结果后输出");
        return result;
    }catch (IllegalArgumentException e){
        System.out.println("获取异常时输出");
        throw e;
    }
    }
}
