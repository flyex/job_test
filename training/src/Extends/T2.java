package Extends;

/**
 * Created by flyex on 2018/9/27.
 */
 class T2 extends T1 {
    private int x;
     T2(){
        super(300);
        System.out.println("子1");
    }
    public T2(int x){
        System.out.println("子2"+x);
        this.x = x;
    }
    public static void main(String[] args){
        //T2 t = new T2();
        T2 t2 = new T2(200);
    }
}
