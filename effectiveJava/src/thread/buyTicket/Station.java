package thread.buyTicket;


//三个车站依次卖票  遇到重复的票等待卖完
public class Station extends Thread {
    public Station(String name){
        super(name);
    }

    static int tick = 1000;
    public Object ob = true;
    String s = "1";
    @Override
    public void run(){
        while (tick>0){
            synchronized (ob){    //多线程同步锁 拿锁
                if(tick>0){
                    System.out.println(this.getName()+"卖出了第"+tick+"张票");
                    tick--;
                }else {
                    System.out.println("票卖完了");
                }
            }
            try{
                this.sleep(1);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}
