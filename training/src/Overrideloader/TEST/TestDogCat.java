package Overrideloader.TEST;

/**
 * Created by flyex on 2018/9/27.
 */
public class TestDogCat {
    public static void show(Animals a){
        if(a instanceof Cats){
            Cats c = new Cats();
            c.eat();
            c.work();
        }else if(a instanceof Dogs){
            Dogs d = new Dogs();
            d.eat();
            d.work();
        }
    }
    public static void main(String[] args){
        show(new Cats());
        show(new Dogs());
        Animals a = new Cats();
        Cats c =(Cats)a;
        c.work();
    }
}
