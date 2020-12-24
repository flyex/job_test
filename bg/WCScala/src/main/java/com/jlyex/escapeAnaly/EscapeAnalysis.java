package com.jlyex.escapeAnaly;

public class EscapeAnalysis {

    static class User{}

    public static void createUser(){
        User user = new User();
    }

    public static void main(String[] args) {

        long a1 = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            createUser();
        }
        // 查看执行时间
        long a2 = System.currentTimeMillis();
        System.out.println("cost " + (a2 - a1) + " ms");
        // 为了方便查看堆内存中对象个数，线程sleep
        try {
            Thread.sleep(100000);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }

    }

}

