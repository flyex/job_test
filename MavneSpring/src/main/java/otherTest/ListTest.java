package otherTest;

import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ListTest {
    private List<Object> list;
    private Set<Object> set;
    private Map<Object,Object> map;
    private String name;

    public List<Object> getList() {
        return list;
    }

    public void setList(List<Object> list) {
        this.list = list;
    }

    public Set<Object> getSet() {
        return set;
    }

    public void setSet(Set<Object> set) {
        this.set = set;
    }

    public Map<Object, Object> getMap() {
        return map;
    }

    public void setMap(Map<Object, Object> map) {
        this.map = map;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;

    }

    public static void main(String[] args){
        ApplicationContext context = new ClassPathXmlApplicationContext("go.xml");

        ListTest listTest = (ListTest)context.getBean("list");

        List list2 = listTest.list;
        List list3 =(List) list2.get(1);
        for(int i=0;i<list3.size();i++){
            System.out.println(list3.get(i));
        }
    }
}
