package FanXing;

/**
 * Created by flyex on 2018/10/13.
 */
public class FanXing {
    public static <X extends Comparable<X>> X maxium(X x,X y,X z){
        X max = x;
        if(y.compareTo(max)>0){
            max = y;
        }
        if(z.compareTo(max)>0){
            max = z;
        }
        return max;
    }

    public static void main(String[] args){
        System.out.printf("%d,%d,%d中最大的数是%d\n",3,4,5,maxium(3,4,5));
        System.out.printf("%.1f,%.1f,%.1f中最大数是%.1f",3.1,5.1,4.4,maxium(3.1,1.1,4.4));
    }
}
