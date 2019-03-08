import java.util.List;

public class PingTong extends Thread {
    String word;
    int delay;


    public PingTong(String word,int delay){
        this.delay=delay;
        this.word=word;
    }

    public void run(){
        try{
            for(;;){
                System.out.println(word+" ");
                sleep(delay);
            }
        }catch (InterruptedException e){
            return;
        }
    }

    public static void main(String[] args){
        new PingTong("ping",33).start();
        new PingTong("Pong",100).start();
    }
}
