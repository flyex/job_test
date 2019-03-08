package thread.interrupte;

import thread.tortoise_rabbit.Interrupte;

public class Test {
    class MyThread extends Thread{
        @Override
        public void run() {
            int i = 0;
            System.out.println(Thread.currentThread().getName() + "begin");

            System.out.println(isInterrupted());
            try {
                System.out.println("睡眠");
                this.sleep(100000);
            }catch (InterruptedException e){
                System.out.println("被中断");
            }
            System.out.println(isInterrupted());
            System.out.println(this.getName()+"end");
        }
        }


    public static void main(String[] args){
        Test test = new Test();
        MyThread myThread = test.new MyThread();
        myThread.start();
        try{
            myThread.sleep(2000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }


        myThread.interrupt(); //只能打断阻塞状态的线程
    }
}
