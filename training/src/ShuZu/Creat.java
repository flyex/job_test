package ShuZu;

import java.util.Scanner;

/**
 * Created by flyex on 2018/9/13.
 */
public class Creat {
    static int[] creat(){
        int[] b = new int[10];
        for(int x = 0;x<10;x++){
            System.out.print("总共可输10个数，请输入第"+(x+1)+"个数:");
            Scanner number = new Scanner(System.in);
            int y = number.nextInt();
            b[x] = y;
        }
        return b;
    }

}
