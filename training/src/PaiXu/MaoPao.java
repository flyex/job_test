package PaiXu;

import java.util.Arrays;

/**
 * Created by flyex on 2018/9/14.
 */
public class MaoPao {
    public static void main(String[] args){
        int[] a ={1,23,4,45,5,3,2,56,11,9};
        for(int i =0;i<a.length-1;i++){
            for(int j=0;j<a.length-1-i;j++){
                if(a[j]>a[j+1]){
                    int x = a[j];
                    a[j] = a[j+1];
                    a[j+1] = x;
                }
            }
        }
        System.out.println(Arrays.toString(a));
    }
}
