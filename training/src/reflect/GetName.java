package reflect;

public class GetName {
    public static void main(String[] args) throws Exception{
        GetName getName = new GetName();
        System.out.println("获取类包类名:"+getName.getClass().getName());
    }
}
