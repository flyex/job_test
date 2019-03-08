package forWhileDo;

/**
 * Created by flyex on 2018/9/12.
 */
import java.util.Scanner;
public class Test6 {
    public static void main(String[] args){
        int i = 1;
        System.out.print("请输入一个数字：");
        Scanner number = new Scanner(System.in);
        int intNumber = number.nextInt();
        for(int x =0;x<=intNumber;x++){

            if(x%4==0&&x%7!=0){
                System.out.print(x + "     ");
                i++;
                if(i>6){
                    System.out.println();
                    i=1;
                }
            }
        }
                }
                }
