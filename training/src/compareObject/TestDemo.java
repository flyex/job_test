package compareObject;

import java.util.stream.IntStream;

public class TestDemo {
    public static void main(String[] args) {
        Objects objects1 = new Objects(1,2,"小李","人");
        Objects objects2 = new Objects(1,2,"小李","狗");
        CompareObject<Objects> compare = new CompareObject<Objects>();
        compare.setCurrent(objects2);
        compare.setOriginal(objects1);
        compare.contrastObj(Objects.class);
        System.out.println(compare.getStatus());

        final String a = "a";
        String b = "b";
        String c = a+b;
        String d = "a"+"b";
        String e = "ab";
        String f = a+"b";
        String str = new String("ab");
        str = str.intern();

        System.out.println(f==str);

        char[] cr = c.toCharArray();
        char aa = 'a';

    }
}
