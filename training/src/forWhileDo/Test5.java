package forWhileDo;

/**
 * Created by flyex on 2018/9/11.
 */
public class Test5 {
    public static void main(String args[]) {
        int [] numbers = {10, 20, 30, 40, 50};

        for(int x : numbers ) {
            if( x == 30 ) {
                System.out.println("哈哈30被吃了");
                continue;
            }
            System.out.print( x );
            System.out.print("\n");
        }
    }
}