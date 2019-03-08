package Exception.Test;

/**
 * Created by flyex on 2018/10/25.
 */
class Demo
{
    int div(int a,int b) throws ArithmeticException,ArrayIndexOutOfBoundsException//在功能上通过throws的关键字声明该功能可能出现问题
    {
        int []arr = new int [a];

        System.out.println(arr[4]);//制造的第一处异常

        return a/b;//制造的第二处异常
    }
}