package com.flyex.web;

public class Test2 {

    public Test test;

    public Test2(Test test){
        this.test = test;
    }

    public static void main(String[] args) {
        Test2 test2 = new Test2(new Test());
        test2.test.print();
    }
}
