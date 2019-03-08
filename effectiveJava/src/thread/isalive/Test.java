package thread.isalive;

import thread.Executor_callable_future.MyCallable;

public class Test {
    public static void main(String[] args) throws InterruptedException{
        int sum = 0;
        MyThread my = new MyThread();
        System.out.println("begin:"+my.isAlive());
        my.start();
        my.join(1);
        System.out.println("end:"+my.isAlive());
    }
}
