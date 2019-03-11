package test.reflect.Method;

public class Methods {
    void go2(){
        System.out.println("default no parameter method");
    }
    public void go1(){
        System.out.println("public no parameter method");
    }
    protected void go3(){
        System.out.println("protected no parameter method");
    }
    private boolean go4(Boolean b){
        System.out.println("private && need returned parameter && int param method"+b);
        return b;
    }


}
