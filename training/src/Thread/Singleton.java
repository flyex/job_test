package Thread;

import java.sql.Statement;

public class Singleton {
    /*
    饿汉模式
    private static Singleton sl = new Singleton();
    public static Singleton getInstance(){
        return sl;
    }

    public static void main(String[] args) {
        Singleton s = Singleton.getInstance();
    }
    */
    private static Singleton sl = null;

    public static Singleton getInstance(){
        if(sl==null){
            synchronized(Singleton.class){
                if (sl==null){
                    sl = new Singleton();
                    return sl;
                }
            }
        }
        return sl;
    }
}
