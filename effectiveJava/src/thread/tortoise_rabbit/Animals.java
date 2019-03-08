package thread.tortoise_rabbit;

public abstract class Animals extends Thread {
     public int length = 1000;

    public abstract void runing();

    @Override
    public void run(){
        super.run();
        while (length>0){
            runing();
        }
    }

    public  interface Stop{
         void win();
    }

    public Stop stop;


}
