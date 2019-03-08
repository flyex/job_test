package RegEx;

/**
 * Created by flyex on 2018/9/17.
 */
public class Test1 {
    public static void main(String[] args) {
        checkQQ("10123134");
    }
    public static void checkQQ(String qq)
    {
        int len = qq.length();
        if(len>=5 && len <=15)
        {
            if(!qq.startsWith("0"))
            {
                try
                {
                    long l = Long.parseLong(qq);
                    System.out.println("qq:"+l);
                }
                catch (NumberFormatException e)
                {
                    System.out.println("出现非法字符");
                }
            }
            else
                System.out.println("不可以0开头");
        }
        else
            System.out.println("QQ号长度错误");
    }
}