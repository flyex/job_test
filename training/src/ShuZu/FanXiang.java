package ShuZu;

import java.util.Arrays;

/**
 * Created by flyex on 2018/9/14.
 */
public class FanXiang {
    public void fanxiang(int[] x){
        int[] a = new int[x.length];
        for(int i =0,j=a.length-1;i<a.length;i++,j--){
            a[j] = x[i];
        }
        System.out.println(Arrays.toString(a));
    }
    public static void main(String[] args){
        int[] x = {1,2,3,4,5,6,7};
        FanXiang go = new FanXiang();
        go.fanxiang(x);
    }
}
