package FanXing;

/**
 * Created by flyex on 2018/10/13.
 */
public class Box<T> {
    private T t;
    public void add(T t){
        this.t = t;
    }

    public T get(){
        return t;
    }

    public static void main(String[] args){
        Box<Integer> inte = new Box<Integer>();
        Box<String> str = new Box<String>();
        //inte.add(new Integer(14));
        inte.add(new Integer(15));
        str.add(new String("德玛西亚"));
        System.out.println(inte.get());
        System.out.println(str.get());
    }
}
