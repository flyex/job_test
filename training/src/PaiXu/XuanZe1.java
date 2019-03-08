package PaiXu;

import java.util.Arrays;

/**
 * Created by flyex on 2018/9/17.
 */
public class XuanZe1 {
    public static void main(String[] args){
        int[] a = {1,23,45,11,25,15,44};
        for(int i = 0;i<a.length-1;i++){
            for(int j = i+1;j<a.length;j++){
                if(a[i]<a[j]){
                    int t = a[i];
                    a[i] = a[j];
                    a[j] = t;
                }
            }
        }
        System.out.println(Arrays.toString(a));
    }
}
