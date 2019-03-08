package Hash;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.*;

/**
 * Created by flyex on 2018/10/12.
 */
public class HashTest {
    public static void main(String[] args){
        Hashtable a = new Hashtable();
        Enumeration name ;
        String str;
        double salary;

        a.put("小敏",4500.00);
        a.put("小明",4000.00);
        a.put("小路",6000.00);
        a.put("小强",5500.00);

        name = a.keys();
        while(name.hasMoreElements()){
            str =(String) name.nextElement();
            System.out.println(str+":薪资是"+a.get(str));
        }

        salary = (Double)a.get("小敏")+1000.11;
        //double newSalary = salary+(double)1000;
        a.put("小敏",salary);
        System.out.println("小敏的新薪资是："+a.get("小敏"));
        //System.out.println(salary);
    }
}
