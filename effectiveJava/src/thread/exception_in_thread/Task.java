package thread.exception_in_thread;

public class Task extends Thread {
    @Override
    public void run() {
        int i = Integer.parseInt("1234");
    }
}
