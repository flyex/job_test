package TestString;

import java.util.*;

/**
 * Created by flyex on 2018/9/13.
 */
public class EqualStr {

   public static void main(String[] args){
       String a = "1234";
       String b = "12"+"34";
       String c = "123";
       String d = c+"4";
       System.out.println(a.equals(b));
       System.out.println(a==b);
       System.out.println(d.equals(b));
       System.out.println(d==b);

   }




}
