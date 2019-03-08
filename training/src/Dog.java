/**
 * Created by flyex on 2018/9/5.
 */
public class Dog {
    int age;
    public Dog(String name){
        System.out.println("小狗姓名：" + name);
    }
    public void SetAge(int setAge){
        age=setAge;
    }
    public int getAge(){
        System.out.println("小狗年纪："+ age);
        return age;
    }

    public static void main (String[] args){
        Dog thisDog=new Dog("花豹");
        thisDog.SetAge(2);
        thisDog.getAge();
        System.out.println("小狗年纪："+ thisDog.age);
    }
}
