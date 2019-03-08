package thread.isalive;

import thread.tortoise_rabbit.Interrupte;

public class MyThread extends Thread {
    @Override
    public void run() {
        System.out.println("run:" + isAlive());
        try {
            this.sleep(1000000);

        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
