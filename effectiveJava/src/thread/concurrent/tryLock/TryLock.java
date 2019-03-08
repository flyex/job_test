package thread.concurrent.tryLock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TryLock extends Thread {
    private Lock lock = new ReentrantLock();
    TimeUnit t = TimeUnit.SECONDS;

    public void run(){
        try {
            if (lock.tryLock()) {
                System.out.println(Thread.currentThread().getName() + "获得锁");

                System.out.println(Thread.currentThread().getName() + "睡眠");
                Thread.sleep(3000);
            } else if (lock.tryLock(5, t)) {
                run();
            }else {
                System.out.println(Thread.currentThread().getName()+"获取锁失败");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();

        }
    }

        public static void main(String[]args){
            TryLock tryLock = new TryLock();
            Thread thread1 = new Thread(tryLock);
            Thread thread2 = new Thread(tryLock);

            thread1.start();

            thread2.start();
        }
    }

