import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class Demo3 {
    public static void main(String[] args) {
        HashMap<Person, String> hm = new HashMap<Person, String>();
        hm.put(new Person("jack", 20), "1001");
        hm.put(new Person("rose", 18), "1002");
        hm.put(new Person("lucy", 19), "1009");
        hm.put(new Person("hmm", 17), "1004");
        hm.put(new Person("ll", 25), "1005");
        System.out.println(hm);
        System.out.println(hm.put(new Person("rose", 18), "1006"));

        Set<Map.Entry<Person, String>> entrySet = hm.entrySet();
        Iterator<Map.Entry<Person, String>> it = entrySet.iterator();
        while (it.hasNext()) {
            Map.Entry<Person, String> next = it.next();
            Person key = next.getKey();
            String value = next.getValue();
            System.out.println(key + " = " + value);
        }
    }
}
