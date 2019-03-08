package otherTest;

public class TestDemo {
    private Test test;

    public Test getTest() {
        return test;
    }

    public TestDemo(Test test){
        this.test=test;
    }

    public void setTest(Test test) {
        this.test = test;
    }

    public static void main(String[] args){
        TestDemo testDemo = new TestDemo(new TestImpl());
        System.out.println(testDemo.getTest());
    }
}
