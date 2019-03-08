package inclass;

/**
 * Created by flyex on 2018/9/5.
 */
public class Out2 {
    private int age=12;

    public void print(final int x) {
        class In {
        public void inPrint() {
            System.out.println("年纪：" + age);
            System.out.println("年纪：" + x);
            }
        }
        new In().inPrint();
    }

}
