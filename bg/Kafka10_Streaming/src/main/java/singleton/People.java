package singleton;

import java.util.Random;

public class People {

    public static People peo = null;

    public static People getInstance(){
        if (null == peo){
            //synchronized (People.class){
                //if (null == peo){
                    peo = new People();
                    return peo;
               // }
           // }
        }
        return peo;
    }

    public void say(){
        System.out.println("hello");
    }

    public static void main(String[] args) {

        for (int i = 0; i < 100; i++) {
            new Thread(()->{
                //People people = People.getInstance();
                People people = new People();
                System.out.println(Thread.currentThread().getName()+ " "+ System.identityHashCode(people));
            },new Random(100)+"").start();
        }

        //People.getInstance().say();

    }
}
