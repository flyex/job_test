package thread.concurrent.reentrantRWLock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReEntrantReadLock {
    private ReentrantReadWriteLock rrw = new ReentrantReadWriteLock();

    public void go(Thread thread){
        rrw.readLock().lock();

        System.out.println(thread.getName()+"开始运行");
        try {
            long start = System.currentTimeMillis();
            while (System.currentTimeMillis() - start <= 1) {
                System.out.println(thread.getName() + "正在读操作");
            }
            System.out.println();
        }finally {
           // rrw.readLock().unlock();
        }

    }

   public static void main(String[] args){
        final ReEntrantReadLock rl = new ReEntrantReadLock();

        new Thread(){
            public void run(){
                rl.go(Thread.currentThread());
            }
        }.start();

       new Thread(){
           public void run(){
               rl.go(Thread.currentThread());
           }
       }.start();
   }
}
