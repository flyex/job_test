package thread.joinTest.Test1;

public class RunnableImpl implements Runnable {
    @Override
    public void run(){
        System.out.println("begin runnable");
        try {
            Thread.sleep(2000);
            System.out.println("runnable ended");
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
