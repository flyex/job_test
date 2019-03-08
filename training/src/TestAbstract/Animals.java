package TestAbstract;

/**
 * Created by flyex on 2018/9/28.
 */
abstract public class Animals {
    private int age = 10;
    public Animals(){

        System.out.println("初始化动物");
    }
    public void move(){
        System.out.println("狗在跑"+age);
    }
}
