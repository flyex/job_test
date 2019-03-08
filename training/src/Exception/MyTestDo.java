package Exception;

/**
 * Created by flyex on 2018/9/26.
 */
public class MyTestDo {
    public static void main(String[] args){
        MyTest test = new MyTest();
        try{
            float x = test.test(3,0);
            System.out.println("x="+x);
        }catch(MyException e){
            System.out.println(e.toString());
            System.out.println("此时的除数是："+e.getValue());
        }/**catch (Exception e){
            System.out.println("大:"+e.getMessage());
        }*/
    }
}
