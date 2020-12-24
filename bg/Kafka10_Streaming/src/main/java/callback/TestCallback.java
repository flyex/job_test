package callback;

public class TestCallback {
    public static void main(String[] args) {

        Worker worker = new Worker();

        new Manager(worker);

    }
}
