package FanXing;

/**
 * Created by flyex on 2018/10/22.
 */
public class Test {
    public <E> void printArray(E[] inputArray){
        for(E x:inputArray){
            System.out.print(x+"     ");
        }
        System.out.println();
    }

    public static void main(String[] args){
        Integer[] a = {1,3,4,5,6,7};
        Double[] b = {1.22,1.11,4.55,4.50};
        Character[] c = {'a','b','b','n','k'};
        Test t = new Test();
        t.printArray(a);
        t.printArray(b);
        t.printArray(c);
    }
}
