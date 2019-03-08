package thread.joinTest;

import thread.tortoise_rabbit.Interrupte;

public class Test {
    class MyThread extends Thread {
        @Override
        public void run() {
            System.out.println("开始执行子线程");
            try {
                Thread.sleep(3000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }

            System.out.println("子线程执行完毕，可以执行主线程了");
        }
    }

    public static void main(String[] args) throws InterruptedException{
        Test test = new Test();
        MyThread myThread = test.new MyThread();
        myThread.start();
        myThread.join();
        System.out.println("等待子线程执行完毕后执行");
    }
}
