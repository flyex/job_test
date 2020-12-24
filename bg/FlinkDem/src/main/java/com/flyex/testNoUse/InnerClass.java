package com.flyex.testNoUse;

public class InnerClass {

    static {
        System.out.println("静态代码块");
    }

    {
        System.out.println("普通代码块");
    }



    public static void main(String[] args) {
        InnerClass in;

        InnerClass in2 = new InnerClass();
        InnerClass in3 = new InnerClass();
    }
}
