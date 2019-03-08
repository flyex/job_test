package extend;

/**
 * Created by flyex on 2018/10/8.
 */
class Father {
    private int a;
    Father(){
        System.out.println("father");
    }
    Father(int a){
        this.a = a;
        System.out.println("father(int a)"+a);
    }
}
