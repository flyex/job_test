package Collections.test;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

public class GroupById {
    public static void main(String[] args) {
        Fruit fruit1 = new Fruit(1,"红苹果",new BigDecimal("4.80"));
        Fruit fruit2 = new Fruit(1,"青苹果",new BigDecimal("6.69"));
        Fruit fruit3 = new Fruit(2,"香蕉",new BigDecimal("3.90"));
        Fruit fruit4 = new Fruit(3,"草莓",new BigDecimal("3.90"));

        List<Fruit> fruitList = new ArrayList<Fruit>();
        fruitList.add(fruit1);
        fruitList.add(fruit2);
        fruitList.add(fruit3);
        fruitList.add(fruit4);

        //System.out.println(fruitList);

        //分组
        Map<Integer,List<Fruit>> groupBy = fruitList.stream().collect(Collectors.groupingBy(Fruit::getId));
        //System.out.println(groupBy);

        //LIst转map
        Map<String,Fruit> fruitMap = fruitList.stream().collect(Collectors.toMap(Fruit::getKinds,a->a));
        Iterator<Map.Entry<String,Fruit>> iterator = fruitMap.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry<String,Fruit> m = iterator.next();
            //System.out.println(m.getKey()+":*****"+m.getValue());
        }
        //过滤符合条件对象
        List<Fruit> fruitList2 = fruitList.stream().filter(a -> a.getMoney().equals(new BigDecimal("3.90"))).collect(Collectors.toList());
        //System.out.println(fruitList2);

        //求和
        BigDecimal totalMoney = fruitList.stream().map(Fruit::getMoney).reduce(BigDecimal.ZERO,BigDecimal::add);
        System.out.println(totalMoney);

    }
}
