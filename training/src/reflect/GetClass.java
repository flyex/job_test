package reflect;

public class GetClass {
    static void test(){
        System.out.println("test");
    }
    void test2(){
        System.out.println("test2");
    }
    public static void main(String[] args) throws Exception{
        Class<?> class1 = null;
        Class<?> class2 = null;
        Class<?> class3 = null;

        class1 = Class.forName("reflect.GetClass");
        class2 = new GetClass().getClass();
        class3 =GetClass.class;



        System.out.println("类名称   " + class1.getName());
        System.out.println("类名称   " + class2.getName());
        System.out.println("类名称   " + class3.getName());
    }
}
