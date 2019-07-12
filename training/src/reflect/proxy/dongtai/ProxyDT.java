package reflect.proxy.dongtai;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class ProxyDT implements InvocationHandler {

    private Object target;
    private Object proxy;

    public ProxyDT(Object target,Object proxy){
        this.target = target;
        this.proxy = proxy;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Exception {
        Class clazz = this.proxy.getClass();
        Method start = clazz.getDeclaredMethod("start");
        start.invoke(this.proxy);
        //原本目标类要用的方法
        Object invoke = method.invoke(this.target,args);
        //代理类设置
        Method end = clazz.getDeclaredMethod("end");
        end.invoke(this.proxy);
        return invoke;
    }
}
