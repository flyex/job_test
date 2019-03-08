package Extends;

/**
 * Created by flyex on 2018/9/27.
 */
 class T1 {
    private int x;
     T1(){
        System.out.println("父1");
    }
     T1(int x){
        System.out.println("父2");
        this.x = x;
    }
}
