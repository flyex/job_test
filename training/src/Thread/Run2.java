package Thread;

/**
 * Created by flyex on 2018/10/18.
 */
public class Run2 implements Runnable{
    public void run(){
        for(int i =1;i<100;i++){
            System.out.println("run2运行时"+i);
        }
    }
}
