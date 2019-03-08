package TestAbstract;

/**
 * Created by flyex on 2018/9/28.
 */
abstract class Dogs extends Animals {
    public Dogs(int age){
        super();
        System.out.println("初始化狗");
    }
    void bark(){
        System.out.println("狗都会叫");
    }
}
