package thread.Executor_callable_future;

import java.util.Date;
import java.util.concurrent.Callable;

public class MyCallable implements Callable<Object> {
    private String taskNum;

    public MyCallable(String task){
        taskNum = task;
    }
    @Override
    public Object call() throws Exception{
        System.out.println(taskNum+":任务开始了");
        Date date1= new Date();
        Thread.sleep(2000);
        Date date2 = new Date();
        System.out.println(taskNum+"：任务结束");
        long time =date2.getTime()-date1.getTime();
        return taskNum+"：任务耗时"+time;
    }
}
