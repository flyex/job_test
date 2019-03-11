package test;

public class Demo {
    public static void main(String[] args) {
        Dad dad = new Son();
        Son son = (Son) dad;
        son.doing();
        dad.doing();
    }
}
