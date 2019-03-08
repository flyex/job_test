package Extends;

/**
 * Created by flyex on 2018/9/26.
 */
public class Animals {
    private String name;
    private int id;
    public Animals(String myName,int myId){
        name = myName;
        id = myId;
    }

    public void eat(){
        System.out.println(name+"正在吃");
    }
    public void sleep(){
        System.out.println(name+"正在睡");
    }
    public void introduction(){
        System.out.println("大家好！我是"+id+"号"+",我的名字是："+name);
    }
    public void bark(){
        System.out.println("动物可以叫");
    }
}
