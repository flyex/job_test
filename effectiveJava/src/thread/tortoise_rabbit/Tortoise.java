package thread.tortoise_rabbit;

public class Tortoise extends Animals{
    static int speed = 50;

    public Tortoise(){
        setName("tortoise");
    }

    @Override
    public void runing(){
        length -= speed;
        System.out.println("乌龟跑了"+speed+"米，还剩下"+length+"米");
        if(length<=0) {
            length = 0;
            System.out.println("乌龟赢了");
            if (stop != null) {
                stop.win();
            }
        }
        try{
            sleep(100);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
