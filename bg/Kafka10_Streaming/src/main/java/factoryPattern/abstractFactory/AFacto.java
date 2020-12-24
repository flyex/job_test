package factoryPattern.abstractFactory;

public class AFacto extends Facto {
    @Override
    Pro getA() {
        return new AAPro();
    }

    @Override
    Pro getB() {
        return new BBPro();
    }
}
