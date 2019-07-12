package reflect.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class PoxyPeople implements InvocationHandler {
    private Object obj;
    public PoxyPeople(Object obj) {
        this.obj = obj;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result = null;
        System.out.println("方法执行之前");
        result = method.invoke(obj,args);
        System.out.println("方法执行之后");
        return result;
    }
}
