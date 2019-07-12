package Collections.hashMap;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HashTest {
    public static void main(String[] args) {
        Student student = new Student("小李",20);
        Student student2 = new Student("小兵",12);
        Student student3 = new Student("小子",16);

        Map<Student,Object> hashMap = new HashMap<Student,Object>();
        hashMap.put(student,"一班");
        hashMap.put(student2,"二班");
        hashMap.put(student3,"三班");

        for(Map.Entry<Student,Object> map:hashMap.entrySet()){
            System.out.println(map.getKey()+"|"+map.getValue());
            System.out.println(hashMap.get(student2));
        }

        
    }
}
