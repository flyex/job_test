package otherTest;

public class TestImpl implements Test {
    String name;
    int age;

    @Override
    public String toString() {
        return name+age;
    }
}
