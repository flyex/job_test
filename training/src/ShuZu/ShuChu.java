package ShuZu;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by flyex on 2018/9/13.
 */
public class ShuChu {
    public void shuchu() {
        int[] c = new Creat().creat();
        System.out.println("请输入你要输出数组的第几个值：");
        Scanner a = new Scanner(System.in);
        int x = a.nextInt();
        System.out.println("这个数组第"+x+"个值是："+c[x-1]);
        System.out.println(Arrays.toString(c));

    }
    public static void main(String[] args){
        ShuChu put = new ShuChu();
        put.shuchu();
    }
}
