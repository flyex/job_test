package reflect.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class TestProxy {
    public static void main(String[] args) {
        People people = new Student();

        InvocationHandler handler = new PoxyPeople(people);

        People proxy = (People) Proxy.newProxyInstance(people.getClass().getClassLoader(),
                people.getClass().getInterfaces(),handler);

        //proxy.work();
        System.out.println(proxy.work2());
        //proxy.work3("读书");
    }
}
