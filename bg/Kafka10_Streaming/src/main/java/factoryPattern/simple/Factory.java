package factoryPattern.simple;

public class Factory {
    public static Producer getP(String type){
        switch (type){
            case "a": return new AP();
            case "b": return new BP();
            default: return null;
        }
    }

    public static void main(String[] args) {
        Factory.getP("b").p();
    }
}
