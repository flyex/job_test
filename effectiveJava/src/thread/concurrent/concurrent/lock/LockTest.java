package thread.concurrent.concurrent.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockTest extends Thread {
    private Lock lock = new ReentrantLock();

    public void run(){
        lock.lock();
        System.out.println(Thread.currentThread().getName()+"获得了锁");
        try{
            this.sleep(3000);
            System.out.println(Thread.currentThread().getName()+"开始休眠");
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            System.out.println(Thread.currentThread().getName()+"释放了锁");
            lock.unlock();
        }
    }

}
