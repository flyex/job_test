package thread.concurrent.volatileTest;

import java.util.concurrent.atomic.AtomicInteger;

public class Test2 {
    public AtomicInteger ai = new AtomicInteger();

    public void add(){
        ai.getAndIncrement();
    }

    public static void main(String[] args) {
        final Test2 test2 = new Test2();
        for(int i=0;i<10;i++){
            new Thread(){
                public void run(){
                    for (int j =0;j<1000;j++){
                        test2.add();
                    }
                }
            }.start();
        }
        while(Thread.activeCount()>1)  //保证前面的线程都执行完
            Thread.yield();
        System.out.println(test2.ai);
    }
}
