package ShuZu;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by flyex on 2018/9/20.
 */
public class StringToInt {
    public static void main(String[] args){
        /**String a = "18817572044";
        */
        Scanner scan = new Scanner(System.in);
        System.out.print("请输入你的身份证号：");
        String a = scan.nextLine();
        int[] b = new int[a.length()];
        for(int i = 0;i<a.length();i++){
            char c = a.charAt(i);
            b[i] = (int)(c-'0');
        }
        int[] year = Arrays.copyOfRange(b,6,10);
        int[] month = Arrays.copyOfRange(b,10,12);
        int[] day = Arrays.copyOfRange(b,12,14);
       for(int tmp:year){
           System.out.print(tmp);
       }System.out.print("年");
       for(int tmp:month){
           System.out.print(tmp);
       }System.out.print("月");
       for(int tmp:day){
           System.out.print(tmp);
       }System.out.print("号");

    }
}
