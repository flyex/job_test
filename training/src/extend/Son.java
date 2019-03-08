package extend;

/**
 * Created by flyex on 2018/10/8.
 */
class Son extends Father {
    private int a;
    Son(){
        super(100);
    }
    Son(int a){

    }
    public static void main(String[] args){
        Son s = new Son(300);
    }
}
