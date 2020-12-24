package thread;

import java.util.concurrent.atomic.AtomicInteger;

public class Ijiajia {
    volatile public static int i = 0;
    public static AtomicInteger atomicInteger = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int j = 0; j < 100000; j++) {
                    i++;
                    atomicInteger.getAndIncrement();
//                    try {
//                        Thread.sleep(100);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
                }
            }
        },"AAA").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int j = 0; j < 100000; j++) {
                    i++;
                    atomicInteger.getAndIncrement();
//                    try {
//                        Thread.sleep(100);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
                }
            }
        },"BBB").start();

        Thread.sleep(20000);
        System.out.println(i);
        System.out.println(atomicInteger.get());


    }
}
