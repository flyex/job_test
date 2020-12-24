package factoryPattern.factory;

public class AF extends Factory2 {
    @Override
    Product getP() {
        return new APP();
    }
}
