package com.test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class DongTaiProxy implements InvocationHandler {
    private Object target;
    private Show show;

    public DongTaiProxy(Object target,Show show){
        this.target = target;
        this.show = show;
    }

    public Object invoke(Object proxy, Method method,Object[] args) throws Throwable{
        this.show.begin();
        method.invoke(this.target,args);
        this.show.close();
        return null;
    }
}


