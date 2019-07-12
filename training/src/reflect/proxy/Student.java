package reflect.proxy;

public class Student implements People {
    @Override
    public void work() {
        System.out.println("我要学习");
    }

    @Override
    public String work2() {
        return "语文";
    }

    @Override
    public void work3(String work) {
        System.out.println("我正在干"+work);
    }
}
