package Exception.Test;

/**
 * Created by flyex on 2018/10/25.
 */
class ExceptionDemo
{
    public static void main(String[]args) //throws Exception
    {
        Demo d = new Demo();

        try
        {
            //int x = d.div(4,0);//程序运行截图中的三组示例 分别对应此处的三行代码
            //int x = d.div(5,0);
            int x = d.div(5,1);
            System.out.println("x="+x);
        }
        catch (ArithmeticException e)
        {
            System.out.println(e.toString());
        }
        catch (ArrayIndexOutOfBoundsException e)
        {
            System.out.println(e.toString());
        }
        catch (Exception e)//父类 写在此处是为了捕捉其他没预料到的异常 只能写在子类异常的代码后面
        //不过一般情况下是不写的
        {
            System.out.println(e.toString());
        }



        System.out.println("Over");
    }

}
