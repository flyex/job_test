package TestString;

/**
 * Created by flyex on 2018/9/12.
 */
public class TestString {
    public static void main(String[] args){
        String a = "www.123.com";
        //char[] b = a;
        int x = a.length();
        System.out.println(x);
        String b ="请登录：";
        String c = b.concat(a); //"请登录：".concat(a)
        System.out.println(c);
    }
}
