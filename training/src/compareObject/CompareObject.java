package compareObject;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class CompareObject<T> {
    private CompareStatus status;
     private T original;
     private T current;

    public CompareStatus getStatus() {
        return status;
    }

    public T getOriginal() {
        return original;
    }

    public void setOriginal(T original) {
        this.original = original;
    }

    public T getCurrent() {
        return current;
    }

    public void setCurrent(T current) {
        this.current = current;
    }

    public void contrastObj(Class<T> cls){
        if(this.original==null){
            this.status = CompareStatus.NEW;
            return;
        }
        if(this.status==CompareStatus.REMOVE){
            return;
        }

        boolean isEuqal = true;
        try{
            Field[] fields = cls.getDeclaredFields();
            for (Field field:fields){
                PropertyDescriptor pd = new PropertyDescriptor(field.getName(),cls);
                Method getMethod = pd.getReadMethod();
                Object o1 = getMethod.invoke(this.original);
                Object o2 = getMethod.invoke(this.current);
                String s1 = o1==null?"":o1.toString();
                String s2 = o2==null?"":o2.toString();
                if(!s1.equals(s2)){
                    isEuqal=false;
                    System.out.println("不一样的属性是："+field.getName()+"属性值是："+"["+s1+s2+"]");
                }
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        if(isEuqal){
            this.status = CompareStatus.NO_CHANGE;
        }else {
            this.status = CompareStatus.CHANGE;
        }
    }
}
