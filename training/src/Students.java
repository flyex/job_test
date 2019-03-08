/**
 * Created by flyex on 2018/9/4.
 */
public class Students {
    int stuAge;
    public Students (String name){
        System.out.println("这位同学的名字是：" + name);
    }

    public void setAge(int age){
        stuAge=age;
    }

    public int getAge(){
        System.out.println("这位同学的年纪是："+ stuAge);
        return stuAge;
    }

    public static void main(String[] args){
        //Students theStudent=new Students("张三");

        Students myStudent=new Students("小王");

        myStudent.setAge(15);
        myStudent.getAge();

        System.out.println("同学年纪是："+ myStudent.getAge() );

    }
}
