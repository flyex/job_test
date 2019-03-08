package thread.tortoise_rabbit;

public class Rabbit extends Animals {
    static int speed = 510;
    public Rabbit(){
        setName("兔子");
    }

    @Override
    public void runing(){
        length -= speed;
        System.out.println("兔子跑了"+speed+"米，距离终点还有"+length+"米");
        if(length<=0){
            length = 0;
            System.out.println("兔子赢了");
            if(stop!=null){
                stop.win();
            }
        }
        try{
            if((1000-length)%510==0){
                sleep(1000);
            }else {
                sleep(100);
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
