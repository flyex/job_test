package exception;

import java.util.HashSet;
import java.util.Scanner;

public class ExceptionDemo {

   public static void heat(int x) throws MyException {
       if(x<100){
           System.out.println("水还没烧滚");
       }else{
           throw new MyException(x);
       }
   }

   public static void main(String[] args) throws Exception{
       try {
          for(int x = 0;x<5;x++) {
              heat(new Scanner(System.in).nextInt());
          }

       }catch (MyException ex){
           ex.printStackTrace();
       }

   }
}
