package ShuZu;

import java.util.Arrays;

/**
 * Created by flyex on 2018/9/17.
 */
public class CanshuFanHuiZhi {
    public static int[] get(int[] a){
        int[] b = new int[a.length];
        for(int i = 0,j=a.length-1;i<a.length;i++,j--){
            b[i] = a[j];
        }
        return b;
    }
    public  static void main(String[] args){
        System.out.println(Arrays.toString(CanshuFanHuiZhi.get(new int[]{1,2,3,4,5,6,7})));
    }

}
