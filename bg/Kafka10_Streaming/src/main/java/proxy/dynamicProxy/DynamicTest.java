package proxy.dynamicProxy;

import proxy.staticProxy.Singer;
import proxy.staticProxy.SingerHello;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class DynamicTest {
    public static void main(String[] args) {

        Singer target = new SingerHello();

        Singer proxy =(Singer) Proxy.newProxyInstance(target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        System.out.println("大家好啊");
                        Object res = method.invoke(target, args);
                        System.out.println("谢谢收看");
                        return res;
                    }
                });
        proxy.sing();
        //proxy.song();
        //System.out.println(target.getClass().getClassLoader().getParent());

    }
}
