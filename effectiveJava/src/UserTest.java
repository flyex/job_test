import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class UserTest {
    static String a = "aaaa";
    static String b = "aaaa";
    static String c = new String("aaaa");
    static String d = new String("aaaa");
    public static void main(String[] args){
        User user = new User(12,"小马");
        User user1 = new User(12,"小马");
        System.out.println(user.equals(user1));
        System.out.println(user.hashCode()+"....."+user1.hashCode());

        List<User> list = new ArrayList<>();
        list.add(new User(12,"x"));
        list.add(new User(13,"x"));
        list.add(new User(14,"x"));
        list.add(new User(15,"x"));
        list.add(new User(16,"x"));
        Collections.sort(list, new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                return o2.age-o1.age;
            }
        });

        System.out.println(list);
    }
}
