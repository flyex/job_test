package otherTest;

public class TestImpl2 implements Test {
    String name="地方";
    int age=2;

    @Override
    public String toString() {
        return name+age;
    }
}
