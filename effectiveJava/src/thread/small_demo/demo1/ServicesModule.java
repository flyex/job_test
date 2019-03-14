package thread.small_demo.demo1;

public class ServicesModule {
    private String message =null;
    private boolean condition = true;

    public synchronized String get(){
        while (condition){
            try {
                wait();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        condition = true;
        notifyAll();
        return message;
    }

    public synchronized void put(String message){
        while (!condition){
            try {
                wait();
            }catch (InterruptedException e){e.printStackTrace();}
        }

        condition = false;
        this.message = message;
        notifyAll();
    }


}
