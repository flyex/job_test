package thread.exception_in_thread;

public class Demo {
    public static void main(String[] args){
        Task task = new Task();
        Thread thread = new Thread(task);thread.setUncaughtExceptionHandler(new Myexception());
        thread.start();
    }
}
