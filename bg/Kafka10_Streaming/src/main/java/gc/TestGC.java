package gc;

public class TestGC {

    @Override
    protected void finalize() throws Throwable {
        System.out.println("gc start");
        super.finalize();
    }

    public static void main(String[] args) {
        TestGC gcc = new TestGC();

        gcc=null;

        System.gc();

    }
}
