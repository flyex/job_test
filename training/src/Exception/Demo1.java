package Exception;

/**
 * Created by flyex on 2018/9/26.
 */
public class Demo1 {
    public static void main(String[] args){
      Demo d = new Demo();
      try{
          int x = d.go(3,-1);
          System.out.println("x="+x);
      }catch(FuShuException e){
          System.out.println(e.toString());
          System.out.println("错误的负数是："+e.getValue());
      }
      System.out.println("over");
    }
}
