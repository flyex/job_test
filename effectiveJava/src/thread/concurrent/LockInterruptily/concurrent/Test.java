package thread.concurrent.LockInterruptily.concurrent;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Test extends Thread{
    private Lock lock = new ReentrantLock();

    public void test() throws InterruptedException{
         lock.lockInterruptibly();
       //lock.lock();
        try{
            System.out.println(Thread.currentThread().getName()+"获得锁");
            System.out.println(Thread.currentThread().getName()+"开始睡眠");
            Thread.sleep(100);
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            System.out.println(Thread.currentThread().getName()+"睡眠结束");
            lock.unlock();
        }
    }

    public void run(){
        try {
            test();
        }catch (InterruptedException e){
            System.out.println(Thread.currentThread().getName()+"被中断");
        }
    }

    public static void main(String[] args){
        Test test = new Test();

        Thread thread1 = new Thread(test);
        Thread thread2 = new Thread(test);

        thread1.start();
        thread2.start();

        /**try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }**/
        thread2.interrupt();
    }
}