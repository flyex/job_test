package PaiXu;

import java.util.Arrays;

/**
 * Created by flyex on 2018/9/14.
 */
public class XuanZe {
    public static void main(String[] args){
        int[] a = {12,1,24,2,10,4,5,9,7};
        for(int i =0;i<a.length-1;i++){
            for(int j=i+1;j<a.length;j++){
                if(a[j]<a[i]){
                    int x = a[j];
                    a[j] = a[i];
                    a[i] = x;
                }
            }
        }
        System.out.println(Arrays.toString(a));
    }
}
