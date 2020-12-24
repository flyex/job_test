package com.jlyex;

public class Sync_Async {

    int ticket = 1000;

    Object o = new Object();

    public void sale() {
        while (true) {
            synchronized (o) {
                if (ticket == 0) {
                    System.out.println("票卖完了");
                    break;
                }
                System.out.println(Thread.currentThread().getName()+ "卖了第" + ticket-- + "张票");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public static void main(String[] args) {

        Sync_Async run1 = new Sync_Async();

        new Thread(() -> {
            run1.sale();
        },"1号售票厅").start();

        new Thread(() -> {
            run1.sale();
        },"2号售票厅").start();

        new Thread(() -> {
            run1.sale();
        },"3号售票厅").start();

        new Thread(() -> {
            run1.sale();
        },"4号售票厅").start();
    }
}
