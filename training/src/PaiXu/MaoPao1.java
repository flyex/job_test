package PaiXu;

import java.util.Arrays;

/**
 * Created by flyex on 2018/9/17.
 */
public class MaoPao1 {
    public static void main(String[] args) {
        int[] a = {1, 4, 34, 11, 23, 10, 8, 5, 46};
        for(int i = 0;i<a.length-1;i++){
            for(int j = i+1;j<a.length;j++){
                if(a[i]<a[j]){
                    int tmp = a[i];
                    a[i] = a[j];
                    a[j] = tmp;
                }
            }

        }
        System.out.print(Arrays.toString(a));
    }
}
