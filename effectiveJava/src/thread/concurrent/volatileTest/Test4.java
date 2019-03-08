package thread.concurrent.volatileTest;

import java.util.concurrent.locks.ReentrantLock;

public class Test4 implements Runnable{
    public int acount = 0;
    ReentrantLock rt = new ReentrantLock();

    public void add(){
        rt.lock();
        try{
            acount++;
        }finally {
            rt.unlock();
        }
    }

    @Override
    public void run() {
        for (int i=0;i<1000;i++){
            add();
        }
    }

    public static void main(String[] args) {
        Test4 test4 = new Test4();
        for(int i=0;i<10;i++){
            new Thread(test4).start();
        }

        /**while (Thread.activeCount()>1){   //让主线程让步
            Thread.yield();
        }**/

        System.out.println(test4.acount);

    }
}
