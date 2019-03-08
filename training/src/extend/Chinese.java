package extend;

/**
 * Created by flyex on 2018/9/6.
 */
public class Chinese extends Person {
    Chinese() {
        super(); // 调用父类构造方法（1）
        prt("子类·调用父类”无参数构造方法“： "+"A chinese coder.");
    }

    Chinese(String name) {
        super(name);// 调用父类具有相同形参的构造方法（2）
        prt("子类·调用父类”含一个参数的构造方法“： "+"his name is " + name);
    }

    Chinese(String name, int age) {
        this(name);// 调用具有相同形参的构造方法（3）
        prt("子类：调用子类具有相同形参的构造方法：his age is " + age);
    }

    public static void main(String[] args) {
        Chinese cn = new Chinese();
        cn = new Chinese("codersai");
        cn = new Chinese("joke", 18);
    }
}
