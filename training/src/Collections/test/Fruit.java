package Collections.test;

import java.math.BigDecimal;

public class Fruit {
    private int id;
    private String kinds;
    private BigDecimal money;

    public String getKinds() {
        return kinds;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public Fruit(int id, String kinds, BigDecimal money) {
        this.id = id;
        this.kinds = kinds;
        this.money = money;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "id="+id+",种类="+kinds+",价格："+money;
    }
}
