package com.flyex.testNoUse;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ThreadJoin {

    class Thread1 implements Runnable{
        @Override
        public void run() {
            try {
                System.out.println("副线程1启动");
                Thread.sleep(1000);
                //Thread.currentThread().join(5000);
                System.out.println("副线程1结束");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    class Thread2 implements Runnable{
        @Override
        public void run() {
            try {
                System.out.println("副线程2启动");
                System.out.println("副线程2yield");
                Thread.yield();
                Thread.sleep(3000);
                //Thread.currentThread().join(5000);
                System.out.println("副线程2结束");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {

        Thread1 thread1 = new ThreadJoin().new Thread1();
        Thread1 thread11 = new ThreadJoin().new Thread1();
        Thread1 thread111 = new ThreadJoin().new Thread1();
        Thread2 thread2 = new ThreadJoin().new Thread2();

        ExecutorService threadPool = Executors.newFixedThreadPool(2);
        threadPool.submit(thread2);
        threadPool.submit(thread1);
        threadPool.submit(thread11);
        threadPool.submit(thread111);

        try {
            threadPool.awaitTermination(15, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        threadPool.shutdown();
        System.out.println("main完成");

    }

}
