package TestAbstract;

/**
 * Created by flyex on 2018/9/28.
 */
public class BigDogs extends Dogs {
    public BigDogs(){
        super(20);
        System.out.println("初始化大狗");
    }
    public static void main(String[] args){
        BigDogs b = new BigDogs();
        b.move();
        b.bark();
    }
}
