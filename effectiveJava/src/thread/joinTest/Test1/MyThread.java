package thread.joinTest.Test1;

public class MyThread extends Thread {
    Thread thread;

    public MyThread(Thread t){
        thread = t;
    }

    @Override
    public void run(){
        synchronized(thread){
            System.out.println("myThread begin");
            try{
                this.sleep(9000);
                System.out.println("myThread end");
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}
