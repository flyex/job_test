package factoryPattern.factory;

public class BF extends Factory2 {
    @Override
    Product getP() {
        return new BPP();
    }
}
