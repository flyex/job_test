package factoryPattern.factory;

public class Test {
    public static void main(String[] args) {
        Factory2 af = new AF();
        af.getP().p();
    }
}
