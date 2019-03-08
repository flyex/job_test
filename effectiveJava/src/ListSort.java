import java.util.*;

public class ListSort {
    public static void sortList(List<Object> list){
        Collections.sort(list, new Comparator<Object>() {
            @Override
            public int compare(Object o1, Object o2) {
                return (int)o2-(int)o1;
            }
        });

    }

    public static void main(String[] args){
        List list = new ArrayList();
        list.add(1);
        list.add(2);
        list.add(6);
        list.add(4);
        list.add(15);
        list.add(8);
        Collections.sort(list);
        System.out.println(list);
    }
}
