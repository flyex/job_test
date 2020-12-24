package callback;

public class Worker {
    public void dosomething(CallBack callBack,String task){
        System.out.println("i am having done"+ task);
        callBack.callback("完成");
    }
}
