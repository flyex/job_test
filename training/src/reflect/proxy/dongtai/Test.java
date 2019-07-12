package reflect.proxy.dongtai;

import reflect.proxy.People;
import reflect.proxy.Student;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class Test {
    public static void main(String[] args) {
        //目标类
        People people = new Student();
        //代理类
        Do doo = new Do();
        InvocationHandler handler = new ProxyDT(people,doo);
        People proxy = (People) Proxy.newProxyInstance(people.getClass().getClassLoader(),
                people.getClass().getInterfaces(),handler);

        System.out.println(proxy.work2());

    }
}
