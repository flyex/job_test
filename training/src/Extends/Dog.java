package Extends;

/**
 * Created by flyex on 2018/9/26.
 */
public class Dog extends Animals{
    private int age;

    public Dog(String myName, int myId,int age) {
        super(myName, myId);
        this.age = age;
    }
    public void bark(){
        super.bark();
        System.out.println("狗可以叫");
    }
    public void age(){
        System.out.println("够的年纪是"+ age);
    }

    public static void main(String[] args){
        Dog dog = new Dog("花花",1,2);
        dog.eat();
        dog.sleep();
        dog.introduction();
        dog.bark();
        dog.age();
    }
}
