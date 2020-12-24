package com.flyex.testNoUse;

public class ReflectTest {

    public static int num = 10;

    static {
        System.out.println("静态代码块");
    }

    {
        System.out.println("普通代码块");
    }

}
