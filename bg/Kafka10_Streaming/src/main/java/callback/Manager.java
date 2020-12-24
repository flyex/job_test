package callback;



public class Manager implements CallBack {


    public Manager(Worker worker){
        worker.dosomething(this,"整理文件");
    }
    @Override
    public void callback(String res) {
        System.out.println("整理文件"+res);
    }
}
