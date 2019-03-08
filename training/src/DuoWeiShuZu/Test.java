package DuoWeiShuZu;

/**
 * Created by flyex on 2018/9/17.
 */
public class Test {
    public static void main(String[] args) {
        String[][] a = new String[4][4];
        a[0] = new String[1];
        a[1] = new String[2];
        a[0][0] = new String("666");
        a[0][1] = new String("777");
        System.out.println(a[0][1]);
    }
}
