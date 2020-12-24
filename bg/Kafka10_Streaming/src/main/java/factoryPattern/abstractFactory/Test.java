package factoryPattern.abstractFactory;

public class Test {
    public static void main(String[] args) {

        AFacto facto = new AFacto();

        facto.getA().p();
        facto.getB().p();

    }
}
