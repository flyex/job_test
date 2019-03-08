package inclass;

/**
 * Created by flyex on 2018/9/5.
 */
class Out {
    private int age = 12;

    public void Print(final int x) {
        class In {
            public void inPrint() {
                System.out.println(x);
                System.out.println(age);
            }
        }
        new In().inPrint();
    }
}